package com.slandrade.sicreditest.domain.vote.controller;

import com.slandrade.sicreditest.domain.associate.service.AssociateService;
import com.slandrade.sicreditest.domain.session.service.SessionService;
import com.slandrade.sicreditest.domain.vote.dto.VoteDTO;
import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import com.slandrade.sicreditest.domain.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/votes")
public class VoteController {

    private final VoteService voteService;
    private final AssociateService associateService;
    private final SessionService sessionService;

    @PostMapping("/")
    public ResponseEntity<Object> createVote(@RequestBody VoteModel voteModel) {

        var voteDTO = VoteDTO.convertToDTO(voteModel);

        var associate = associateService.getById(voteDTO.getAssociateId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Associate not found"));

        var session = sessionService.getById(voteDTO.getSessionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Session not found"));

        var voteCreated = new VoteModel(voteDTO, associate, session);

        if (voteService.checkIfAssociateAlreadyVoted(voteCreated)) {
            return ResponseEntity
                    .internalServerError()
                    .body("Associate already voted in this topic");
        }

        if (Objects.isNull(voteCreated.getVote())) {
            return ResponseEntity
                    .badRequest()
                    .body("Vote not found");
        }

        if (session.getEndAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity
                    .internalServerError()
                    .body("Session is closed");
        }

        session.getVotes().add(voteCreated);

        try {
            voteService.createVote(voteCreated);
            return ResponseEntity.ok(voteCreated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}