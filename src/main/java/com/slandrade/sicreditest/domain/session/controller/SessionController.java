package com.slandrade.sicreditest.domain.session.controller;

import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.session.service.SessionService;
import com.slandrade.sicreditest.domain.topic.service.TopicService;
import com.slandrade.sicreditest.domain.vote.enums.VoteEnum;
import com.slandrade.sicreditest.domain.session.dto.SessionDTO;
import com.slandrade.sicreditest.domain.vote.dto.VoteSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sessions")
public class SessionController {

    private final SessionService sessionService;
    private final TopicService topicService;

    @PostMapping("/")
    public ResponseEntity<Object> createSession(@RequestBody SessionModel sessionModel) {

        var sessionDTO = SessionDTO.convertToDTO(sessionModel);
        var topic = topicService.getById(sessionDTO.getTopicId());

        if (sessionDTO.getStartAt() == null) {
            return ResponseEntity.badRequest().body("Start date is required");
        }

        if (sessionDTO.getEndAt() == null) {
            sessionDTO.setEndAt(sessionDTO.getStartAt().plusMinutes(1));
        }

        if (sessionDTO.getEndAt().isBefore(sessionDTO.getStartAt())) {
            return ResponseEntity.badRequest().body("End date must be after start date");
        }

        if (topic.isEmpty()) {
            return ResponseEntity.badRequest().body("Topic not found");
        }

        try {
            var sessionCreated = new SessionModel(
                    sessionDTO.getStartAt(),
                    sessionDTO.getEndAt(),
                    topic.get());

            sessionService.createSession(sessionCreated);

            return ResponseEntity.ok().body(sessionCreated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/votesCount/{id}")
    public ResponseEntity<Object> votesCount(@PathVariable(value="id") UUID sessionId) {

        var sessionModel = sessionService.getById(sessionId);

        if (sessionModel.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Session not found");
        }

        var voteSummary = VoteSummaryDTO.builder()
                .negativeVotes(votesCount(sessionModel, VoteEnum.NO))
                .positiveVotes(votesCount(sessionModel, VoteEnum.YES))
                .topicDescription(sessionModel.get().getTopic().getDescription())
                .build();

        return new ResponseEntity<>(voteSummary, HttpStatus.OK);
    }

    private static int votesCount(Optional<SessionModel> sessionModel, VoteEnum voteEnum) {

        return sessionModel.map(model -> Math.toIntExact(
                model.getVotes()
                        .stream()
                        .filter(vote -> vote.getVote() == voteEnum)
                        .count()))
                .orElse(0);
    }
}