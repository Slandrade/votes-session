package com.slandrade.sicreditest.domain.session.dto;

import com.slandrade.sicreditest.domain.session.model.SessionModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SessionDTO {

    private final UUID id;
    private final UUID topicId;
    private final LocalDateTime startAt;
    private LocalDateTime endAt;

    public static SessionDTO convertToDTO(SessionModel sessionModel) {

        return SessionDTO.builder()
                .id(sessionModel.getId())
                .startAt(LocalDateTime.now())
                .endAt(sessionModel.getEndAt())
                .topicId(sessionModel.getTopic().getId())
                .build();
    }
}