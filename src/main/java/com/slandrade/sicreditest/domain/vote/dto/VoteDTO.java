package com.slandrade.sicreditest.domain.vote.dto;

import com.slandrade.sicreditest.domain.vote.enums.VoteEnum;
import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class VoteDTO {

    private final UUID id;
    private final VoteEnum vote;
    private final UUID sessionId;
    private final UUID associateId;

    public static VoteDTO convertToDTO(VoteModel voteModel) {

        return VoteDTO.builder()
                .id(voteModel.getId())
                .vote(voteModel.getVote())
                .associateId(voteModel.getAssociate().getId())
                .sessionId(voteModel.getSession().getId())
                .build();
    }
}