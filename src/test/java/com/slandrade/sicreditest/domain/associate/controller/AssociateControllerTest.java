package com.slandrade.sicreditest.domain.associate.controller;

import com.slandrade.sicreditest.domain.associate.dto.AssociateDTO;
import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.associate.service.AssociateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociateControllerTest {

    private static final String NAME = "Test";
    private static final String CPF = "12345678900";

    @InjectMocks
    private AssociateController associateController;

    @Mock
    private AssociateService associateService;

    @Test
    @DisplayName("Should return ok when associate is created")
    void testCreateAssociate() {

        var request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var associateModel = new AssociateModel();
        associateModel.setName(NAME);
        associateModel.setCpf(CPF);

        when(associateService.createAssociate(associateModel)).thenReturn(associateModel);

        var associateDTO = AssociateDTO.convertToDTO(associateModel);
        var responseEntity = associateController.createAssociate(associateModel);

        assertThat(ResponseEntity.ok(associateDTO))
                .usingRecursiveComparison()
                .isEqualTo(responseEntity);
    }

    @Test
    @DisplayName("Should return bad request when name or cpf is null")
    void testCreateAssociate_BadRequest() {

        var request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var associateModel = new AssociateModel();
        when(associateService.createAssociate(associateModel)).thenReturn(associateModel);

        var responseEntity = associateController.createAssociate(associateModel);

        assertEquals(ResponseEntity.badRequest().body("Name and CPF are required"), responseEntity);
    }
}