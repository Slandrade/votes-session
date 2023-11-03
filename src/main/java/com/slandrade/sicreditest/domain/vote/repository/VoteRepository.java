package com.slandrade.sicreditest.domain.vote.repository;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<VoteModel, UUID> {

    boolean existsByAssociateAndSession(AssociateModel associate, SessionModel session);
}