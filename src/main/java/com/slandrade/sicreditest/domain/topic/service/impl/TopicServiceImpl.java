package com.slandrade.sicreditest.domain.topic.service.impl;

import com.slandrade.sicreditest.domain.topic.repository.TopicRepository;
import com.slandrade.sicreditest.domain.topic.service.TopicService;
import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    @Override
    public TopicModel createTopic(TopicModel topicModel) {
        return repository.save(topicModel);
    }

    @Override
    public List<TopicModel> getAllTopics() {
        return repository.findAll();
    }

    @Override
    public Optional<TopicModel> getById(UUID topicId) {
        return repository.findById(topicId);
    }
}