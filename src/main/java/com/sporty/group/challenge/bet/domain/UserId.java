package com.sporty.group.challenge.bet.domain;

public record UserId(String value) {
    public UserId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("UserId is required");
    }
}
