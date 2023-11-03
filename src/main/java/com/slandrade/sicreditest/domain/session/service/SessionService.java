package com.slandrade.sicreditest.domain.session.service;

import com.slandrade.sicreditest.domain.session.model.SessionModel;

import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    void createSession(SessionModel sessionModel);
    Optional<SessionModel> getById(UUID sessionId);
}