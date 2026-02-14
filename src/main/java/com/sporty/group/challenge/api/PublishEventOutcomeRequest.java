package com.sporty.group.challenge.api;

public record PublishEventOutcomeRequest(
        String eventId,
        String eventName,
        String eventWinnerId
) {}
