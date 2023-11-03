package com.slandrade.sicreditest.domain.vote.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteSummaryDTO {

    private Integer positiveVotes;
    private Integer negativeVotes;
    private String topicDescription;
}