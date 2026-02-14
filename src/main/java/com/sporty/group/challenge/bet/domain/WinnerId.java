package com.sporty.group.challenge.bet.domain;

public record WinnerId(String value) {
    public WinnerId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("WinnerId is required");
    }
}
