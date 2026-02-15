package com.sporty.group.challenge.event.action.api;

public record PublishSportEventOutcomeRequest(
        String eventId,
        String eventName,
        String eventWinnerId
) {}
