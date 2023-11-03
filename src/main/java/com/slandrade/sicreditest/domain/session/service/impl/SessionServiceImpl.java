package com.slandrade.sicreditest.domain.session.service.impl;

import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.session.repository.SessionRepository;
import com.slandrade.sicreditest.domain.session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository repository;

    @Override
    public void createSession(SessionModel sessionModel) {
        repository.save(sessionModel);
    }

    @Override
    public Optional<SessionModel> getById(UUID sessionId) {
        return repository.findById(sessionId);
    }
}