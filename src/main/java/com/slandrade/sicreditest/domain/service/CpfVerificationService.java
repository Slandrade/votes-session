package com.slandrade.sicreditest.domain.service;

import lombok.AllArgsConstructor;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CpfVerificationService {

    private final RestTemplate restTemplate;
    private final Environment environment;

    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

    public String isCpfAbleToVote(String cpf) {
        try {
            String cpfServiceUrl = environment.getProperty("cpf.service.url") + cpf;
            ResponseEntity<String> response = restTemplate.exchange(cpfServiceUrl, HttpMethod.GET, null, String.class);

            String responseMessage = response.getBody();

            if (ABLE_TO_VOTE.equals(responseMessage)) {
                return ABLE_TO_VOTE;
            } else if (ResponseEntity.ok(UNABLE_TO_VOTE).equals(response)) {
                return UNABLE_TO_VOTE;
            } else {
                throw new IntegrationException("Unexpected response from CPF service.");
            }
        } catch (HttpStatusCodeException ex) {
            if (HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
                throw new IntegrationException("Cpf not found.");
            } else {
                throw new IntegrationException("An error occurred while attempting to access the 'CpfVerificationService'. Please try again later");
            }
        }
    }
}