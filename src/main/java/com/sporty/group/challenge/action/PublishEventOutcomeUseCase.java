package com.sporty.group.challenge.action;

import com.sporty.group.challenge.domain.EventOutcome;
import com.sporty.group.challenge.domain.EventOutcomePublisher;
import org.springframework.stereotype.Service;

@Service
public class PublishEventOutcomeUseCase {

    private final EventOutcomePublisher publisher;

    public PublishEventOutcomeUseCase(EventOutcomePublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(EventOutcome eventOutcome) {
        publisher.publish(eventOutcome);
    }
}
