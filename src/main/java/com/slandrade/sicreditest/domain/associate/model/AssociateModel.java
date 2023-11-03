package com.slandrade.sicreditest.domain.associate.model;

import com.slandrade.sicreditest.domain.vote.model.VoteModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tb_associates")
public class AssociateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL)
    private List<VoteModel> votes;
}