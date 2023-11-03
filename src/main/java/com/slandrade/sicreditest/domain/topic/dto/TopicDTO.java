package com.slandrade.sicreditest.domain.topic.dto;

import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TopicDTO {

    private final UUID id;
    private final String description;

    public static TopicDTO convertToDTO(TopicModel topicModel) {
        return TopicDTO.builder()
                .id(topicModel.getId())
                .description(topicModel.getDescription())
                .build();
    }

    public static List<TopicDTO> convertListToDTO(List<TopicModel> topics) {

        return topics.stream()
                .map(TopicDTO::convertToDTO)
                .toList();
    }
}