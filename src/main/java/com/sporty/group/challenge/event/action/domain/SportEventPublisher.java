package com.sporty.group.challenge.event.action.domain;

public interface SportEventPublisher {
    void publish(SportEventOutcome sportEventOutcome);
}