package com.sporty.group.challenge.event.action.api;

public record PublishEventOutcomeRequest(
        String eventId,
        String eventName,
        String eventWinnerId
) {}
