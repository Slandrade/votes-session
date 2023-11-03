package com.slandrade.sicreditest.domain.topic.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import com.slandrade.sicreditest.domain.topic.repository.TopicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private TopicRepository repository;

    @Test
    @DisplayName("Should create topic")
    void testCreateTopic() {

        var topicModel = new TopicModel();

        when(repository.save(topicModel)).thenReturn(topicModel);

        var result = topicService.createTopic(topicModel);

        assertAll(
                () -> assertEquals(topicModel, result),
                () -> verify(repository, times(1)).save(topicModel)
        );
    }

    @Test
    @DisplayName("Should return all topics")
    void testGetAllTopics() {

        var topics = Collections.singletonList(new TopicModel());

        when(repository.findAll()).thenReturn(topics);

        var result = topicService.getAllTopics();

        assertAll(
                () -> assertEquals(topics, result),
                () -> verify(repository, times(1)).findAll()
        );
    }

    @Test
    @DisplayName("Should return topic when topic exists")
    void testGetById() {

        var topicId = UUID.randomUUID();
        var topicModel = Optional.of(new TopicModel());

        when(repository.findById(topicId)).thenReturn(topicModel);

        var result = topicService.getById(topicId);

        assertAll(
                () -> assertEquals(topicModel, result),
                () -> verify(repository, times(1)).findById(topicId)
        );
    }
}