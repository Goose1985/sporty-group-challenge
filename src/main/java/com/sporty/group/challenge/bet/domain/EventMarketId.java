package com.sporty.group.challenge.bet.domain;

public record EventMarketId(String value) {
    public EventMarketId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("EventMarketId is required");
    }
}
