package com.slandrade.sicreditest.domain.vote.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import com.slandrade.sicreditest.domain.vote.repository.VoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private VoteRepository voteRepository;

    @Test
    @DisplayName("Should return true when associate is able to vote")
    void testCreateVote() {

        var voteModel = new VoteModel();
        voteModel.setAssociate(new AssociateModel());
        voteModel.setSession(new SessionModel());

        when(voteRepository.save(voteModel)).thenReturn(voteModel);

        var createdVote = voteService.createVote(voteModel);

        assertAll(
                () -> verify(voteRepository, times(1)).save(voteModel),
                () -> assertNotNull(createdVote.getVotedAt())
        );
    }

    @Test
    @DisplayName("Should return true when associate already voted")
    void testCheckIfAssociateAlreadyVoted() {

        var voteModel = new VoteModel();
        voteModel.setAssociate(new AssociateModel());
        voteModel.setSession(new SessionModel());

        when(voteRepository.existsByAssociateAndSession(voteModel.getAssociate(), voteModel.getSession()))
                .thenReturn(true);

        boolean alreadyVoted = voteService.checkIfAssociateAlreadyVoted(voteModel);

        assertAll(
                () -> verify(voteRepository, times(1))
                        .existsByAssociateAndSession(voteModel.getAssociate(), voteModel.getSession()),
                () -> assertTrue(alreadyVoted)
        );
    }
}