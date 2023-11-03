package com.slandrade.sicreditest.domain.vote.model;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.session.model.SessionModel;
import com.slandrade.sicreditest.domain.vote.enums.VoteEnum;
import com.slandrade.sicreditest.domain.vote.dto.VoteDTO;
import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_votes")
public class VoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private VoteEnum vote;

    private LocalDateTime votedAt;

    @OneToOne
    private AssociateModel associate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private SessionModel session;

    public VoteModel(VoteDTO voteDTO, AssociateModel associate, SessionModel session) {
        this.vote = voteDTO.getVote();
        this.associate = associate;
        this.session = session;
        this.votedAt = LocalDateTime.now();
    }
}