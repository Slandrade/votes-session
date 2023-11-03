package com.slandrade.sicreditest.domain.vote.service;

import com.slandrade.sicreditest.domain.vote.model.VoteModel;

public interface VoteService {
    VoteModel createVote(VoteModel voteModel);
    boolean checkIfAssociateAlreadyVoted(VoteModel voteModel);
}
