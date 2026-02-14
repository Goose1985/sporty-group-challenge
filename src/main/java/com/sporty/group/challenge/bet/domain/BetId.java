package com.sporty.group.challenge.bet.domain;

public record BetId(String value) {
    public BetId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("BetId is required");
    }
}