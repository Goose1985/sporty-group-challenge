package com.sporty.group.challenge.bet.domain;

public record EventId(String value) {
    public EventId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("EventId is required");
    }
}
