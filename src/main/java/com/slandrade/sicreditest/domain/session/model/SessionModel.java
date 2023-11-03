package com.slandrade.sicreditest.domain.session.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slandrade.sicreditest.domain.topic.model.TopicModel;
import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_sessions")
public class SessionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @OneToOne
    private TopicModel topic;

    @JsonIgnore
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<VoteModel> votes = new ArrayList<>();

    public SessionModel(LocalDateTime startAt, LocalDateTime endAt, TopicModel topicModel) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.topic = topicModel;
    }
}