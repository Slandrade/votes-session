package com.slandrade.sicreditest.domain.topic.controller;

import com.slandrade.sicreditest.domain.topic.dto.TopicDTO;
import com.slandrade.sicreditest.domain.topic.service.TopicService;
import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/topics")
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/")
    public ResponseEntity<Object> createTopic(@RequestBody TopicModel topicModel) {

        var topicCreated = topicService.createTopic(topicModel);
        var topicDTO = TopicDTO.convertToDTO(topicCreated);

        return ResponseEntity.ok(topicDTO);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getALLTopics() {

        var topicDTO = TopicDTO.convertListToDTO(topicService.getAllTopics());

        return ResponseEntity.ok(topicDTO);
    }
}