package com.slandrade.sicreditest.domain.vote.service.impl;

import com.slandrade.sicreditest.domain.service.CpfVerificationService;
import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import com.slandrade.sicreditest.domain.vote.repository.VoteRepository;
import com.slandrade.sicreditest.domain.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final CpfVerificationService cpfVerificationService;

    public VoteModel createVote(VoteModel voteModel) {

        cpfVerificationService.isCpfAbleToVote(voteModel.getAssociate().getCpf());
        voteModel.setVotedAt(LocalDateTime.now());
        return voteRepository.save(voteModel);
    }

    @Override
    public boolean checkIfAssociateAlreadyVoted(VoteModel voteModel) {
        return voteRepository.existsByAssociateAndSession(voteModel.getAssociate(), voteModel.getSession());
    }
}