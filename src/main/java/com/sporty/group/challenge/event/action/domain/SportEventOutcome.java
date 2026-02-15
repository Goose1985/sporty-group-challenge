package com.sporty.group.challenge.event.action.domain;

public record SportEventOutcome(
        String eventId,
        String eventName,
        String eventWinnerId
) {}
