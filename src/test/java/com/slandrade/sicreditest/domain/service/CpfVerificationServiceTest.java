package com.slandrade.sicreditest.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.boot.beanvalidation.IntegrationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CpfVerificationServiceTest {

    private static final String CPF_SERVICE_URL = "http://cpf.service.url/";
    private static final String CPF_PROPERTY = "cpf.service.url";
    private static final String CPF = "12345678901";
    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

    @InjectMocks
    private CpfVerificationService cpfVerificationService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Environment environment;

    @Test
    @DisplayName("Should return 'ABLE_TO_VOTE' when cpf service returns 'ABLE_TO_VOTE'")
    void testIsCpfAbleToVote_Able() {

        var cpfServiceUrl = CPF_SERVICE_URL + CPF;

        when(environment.getProperty(CPF_PROPERTY)).thenReturn(CPF_SERVICE_URL);
        when(restTemplate.exchange(cpfServiceUrl, HttpMethod.GET, null, String.class))
                .thenReturn(new ResponseEntity<>(ABLE_TO_VOTE, HttpStatus.OK));

        String result = cpfVerificationService.isCpfAbleToVote(CPF);

        assertEquals(ABLE_TO_VOTE, result);
    }

    @Test
    @DisplayName("Should return 'UNABLE_TO_VOTE' when cpf service returns 'UNABLE_TO_VOTE'")
    void testIsCpfAbleToVote_Unable() {

        var cpfServiceUrl = CPF_SERVICE_URL + CPF;

        when(environment.getProperty(CPF_PROPERTY)).thenReturn(CPF_SERVICE_URL);
        when(restTemplate.exchange(cpfServiceUrl, HttpMethod.GET, null, String.class)).thenReturn(ResponseEntity.ok(UNABLE_TO_VOTE));

        String result = cpfVerificationService.isCpfAbleToVote(CPF);

        assertEquals(UNABLE_TO_VOTE, result);
    }

    @Test
    @DisplayName("Should throw IntegrationException when cpf service returns unexpected response")
    void testIsCpfAbleToVote_NotFound() {

        var cpfServiceUrl = CPF_SERVICE_URL + CPF;

        when(environment.getProperty(CPF_PROPERTY)).thenReturn(CPF_SERVICE_URL);
        when(restTemplate.exchange(cpfServiceUrl, HttpMethod.GET, null, String.class))
                .thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {});

        assertThrows(IntegrationException.class, () -> cpfVerificationService.isCpfAbleToVote(CPF));
    }
}