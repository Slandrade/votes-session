package com.slandrade.sicreditest.domain.vote.controller;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.associate.service.AssociateService;
import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.session.service.SessionService;
import com.slandrade.sicreditest.domain.vote.enums.VoteEnum;
import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import com.slandrade.sicreditest.domain.vote.service.VoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteControllerTest {

    private static final UUID SESSION_ID = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");

    @InjectMocks
    private VoteController voteController;

    @Mock
    private VoteService voteService;

    @Mock
    private AssociateService associateService;

    @Mock
    private SessionService sessionService;

    @Test
    void testCreateVote() {

        var associateModel = new AssociateModel();
        associateModel.setId(SESSION_ID);

        var sessionModel = new SessionModel();
        sessionModel.setId(SESSION_ID);
        sessionModel.setEndAt(LocalDateTime.now().plusMinutes(1));

        var voteModel = new VoteModel();
        voteModel.setVote(VoteEnum.YES);
        voteModel.setAssociate(associateModel);
        voteModel.setSession(sessionModel);

        when(associateService.getById(SESSION_ID)).thenReturn(Optional.of(associateModel));
        when(sessionService.getById(SESSION_ID)).thenReturn(Optional.of(sessionModel));
        when(voteService.checkIfAssociateAlreadyVoted(any(VoteModel.class))).thenReturn(false);
        when(voteService.createVote(any(VoteModel.class))).thenReturn(voteModel);

        var response = voteController.createVote(voteModel);

        assertAll(
                () -> verify(associateService, times(1)).getById(SESSION_ID),
                () -> verify(sessionService, times(1)).getById(SESSION_ID),
                () -> verify(voteService, times(1)).checkIfAssociateAlreadyVoted(any(VoteModel.class)),
                () -> verify(voteService, times(1)).createVote(any(VoteModel.class)),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertThat(voteModel)
                        .usingRecursiveComparison()
                        .ignoringFields("votedAt")
                        .isEqualTo(response.getBody())
        );
    }
}
