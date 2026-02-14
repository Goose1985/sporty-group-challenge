package com.sporty.group.challenge.event.action.domain;

public interface EventOutcomePublisher {
    void publish(EventOutcome eventOutcome);
}