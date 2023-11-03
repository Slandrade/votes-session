package com.slandrade.sicreditest.domain.associate.repository;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociateRepository extends JpaRepository<AssociateModel, UUID> {
}