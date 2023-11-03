package com.slandrade.sicreditest.domain.associate.service.impl;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.associate.repository.AssociateRepository;
import com.slandrade.sicreditest.domain.associate.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository repository;
    @Override
    public Optional<AssociateModel> getById(UUID associateId) {
        return repository.findById(associateId);
    }

    @Override
    public AssociateModel createAssociate(AssociateModel associateModel) {
        return repository.save(associateModel);
    }
}