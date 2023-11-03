package com.slandrade.sicreditest.domain.associate.service;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;

import java.util.Optional;
import java.util.UUID;

public interface AssociateService {
    Optional<AssociateModel> getById(UUID associateId);
    AssociateModel createAssociate(AssociateModel associateModel);
}