package com.slandrade.sicreditest.domain.session.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.slandrade.sicreditest.domain.vote.dto.VoteSummaryDTO;
import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.session.service.SessionService;
import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import com.slandrade.sicreditest.domain.topic.service.TopicService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionControllerTest {

    private static final UUID SESSION_ID = UUID.randomUUID();
    private static final LocalDateTime START_AT = LocalDateTime.of(2023, 11, 3, 22, 0, 0);

    @InjectMocks
    private SessionController sessionController;

    @Mock
    private SessionService sessionService;

    @Mock
    private TopicService topicService;

    @Test
    @DisplayName("Should return ok when session is created")
    void testCreateSession() {

        var topicModel = new TopicModel();
        topicModel.setId(SESSION_ID);

        var sessionModel = new SessionModel();
        sessionModel.setStartAt(START_AT);
        sessionModel.setEndAt(START_AT.plusMinutes(1));
        sessionModel.setTopic(topicModel);

        when(topicService.getById(SESSION_ID)).thenReturn(Optional.of(topicModel));

        var response = sessionController.createSession(sessionModel);

        assertAll(
                () -> assertEquals(ResponseEntity.class, response.getClass()),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotEquals(sessionModel.getEndAt(), sessionModel.getStartAt()),
                () -> assertThat(sessionModel.getEndAt()).isAfter(sessionModel.getStartAt()),
                () -> assertThat(sessionModel.getEndAt()).isAfter(LocalDateTime.now())
        );
    }

    @Test
    @DisplayName("Should return the votes count")
    void testVotesCount() {

        var sessionModel = new SessionModel();
        sessionModel.setId(SESSION_ID);
        sessionModel.setTopic(new TopicModel());

        when(sessionService.getById(SESSION_ID)).thenReturn(Optional.of(sessionModel));

        var response = sessionController.votesCount(SESSION_ID);
        var voteSummary = Objects.requireNonNull((VoteSummaryDTO) response.getBody());

        verify(sessionService, times(1)).getById(SESSION_ID);

        assertAll(
                () -> assertEquals(ResponseEntity.class, response.getClass()),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(voteSummary),
                () -> assertEquals(0, voteSummary.getNegativeVotes()),
                () -> assertEquals(0, voteSummary.getPositiveVotes())
        );
    }
}