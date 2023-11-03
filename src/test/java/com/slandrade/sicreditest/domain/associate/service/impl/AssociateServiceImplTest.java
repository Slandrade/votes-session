package com.slandrade.sicreditest.domain.associate.service.impl;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.associate.repository.AssociateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociateServiceImplTest {

    private static final UUID ID = UUID.fromString("24a151b2-e042-4e3b-93f2-b1a3684ddf81");

    @InjectMocks
    private AssociateServiceImpl associateService;

    @Mock
    private AssociateRepository repository;

    @Test
    @DisplayName("Should get an associate by id")
    void testGetById() {

        var associateModel = new AssociateModel();

        when(repository.findById(ID)).thenReturn(Optional.of(associateModel));

        var result = associateService.getById(ID);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(Optional.of(associateModel), result),
                () -> verify(repository, times(1)).findById(ID)
        );
    }

    @Test
    @DisplayName("Should create an associate")
    void testCreateAssociate() {

        var associateModel = new AssociateModel();

        when(repository.save(associateModel)).thenReturn(associateModel);

        var result = associateService.createAssociate(associateModel);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(associateModel, result),
                () -> verify(repository, times(1)).save(associateModel)
        );
    }
}