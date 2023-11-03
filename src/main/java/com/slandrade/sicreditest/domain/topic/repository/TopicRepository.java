package com.slandrade.sicreditest.domain.topic.repository;

import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<TopicModel, UUID> {
}