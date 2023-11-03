package com.slandrade.sicreditest.domain.session.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.session.repository.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository repository;

    @Test
    @DisplayName("Should create session")
    void testCreateSession() {

        var sessionModel = new SessionModel();

        when(repository.save(sessionModel)).thenReturn(sessionModel);

        sessionService.createSession(sessionModel);

        verify(repository, times(1)).save(sessionModel);
    }

    @Test
    @DisplayName("Should return session when session exists")
    void testGetById() {

        var sessionId = UUID.randomUUID();
        var sessionModel = new SessionModel();

        when(repository.findById(sessionId)).thenReturn(Optional.of(sessionModel));

        var result = sessionService.getById(sessionId);

        assertAll(
                () -> assertEquals(Optional.of(sessionModel), result),
                () -> verify(repository, times(1)).findById(sessionId)
        );
    }
}