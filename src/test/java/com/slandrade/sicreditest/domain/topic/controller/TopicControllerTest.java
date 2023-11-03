package com.slandrade.sicreditest.domain.topic.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.slandrade.sicreditest.domain.topic.dto.TopicDTO;
import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import com.slandrade.sicreditest.domain.topic.service.TopicService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicControllerTest {

    @InjectMocks
    private TopicController topicController;

    @Mock
    private TopicService topicService;

    @Test
    @DisplayName("Should return ok when topic is created")
    void testCreateTopic() {

        var topicModel = new TopicModel();

        when(topicService.createTopic(topicModel)).thenReturn(topicModel);

        var result = topicController.createTopic(topicModel);

        assertAll(
                () -> assertEquals(HttpStatus.OK, result.getStatusCode()),
                () -> assertThat(topicModel)
                        .usingRecursiveComparison()
                        .isEqualTo(Objects.requireNonNull(result.getBody()))
        );
    }

    @Test
    @DisplayName("Should return ok when get all topics")
    void testGetAllTopics() {

        var topics = Collections.singletonList(new TopicModel());

        when(topicService.getAllTopics()).thenReturn(topics);

        var result = topicController.getALLTopics();

        assertAll(
                () -> assertEquals(HttpStatus.OK, result.getStatusCode()),
                () -> assertThat(topics)
                        .usingRecursiveComparison()
                        .isEqualTo(Objects.requireNonNull(result.getBody()))
        );
    }
}