package com.sporty.group.challenge.event.action.domain;

public record EventOutcome(
        String eventId,
        String eventName,
        String eventWinnerId
) {}
