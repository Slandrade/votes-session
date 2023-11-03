package com.slandrade.sicreditest.domain.session.repository;

import com.slandrade.sicreditest.domain.session.model.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<SessionModel, UUID> {
}