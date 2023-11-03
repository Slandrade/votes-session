package com.slandrade.sicreditest.domain.vote.enums;

import lombok.Getter;

@Getter
public enum VoteEnum {
    YES("yes"),
    NO("no");

    private final String vote;

    VoteEnum(String vote) {
        this.vote = vote;
    }
}