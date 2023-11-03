package com.slandrade.sicreditest.domain.topic.service;

import com.slandrade.sicreditest.domain.topic.model.TopicModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TopicService {
    TopicModel createTopic(TopicModel topicModel);
    List<TopicModel> getAllTopics();
    Optional<TopicModel> getById(UUID topicId);
}