package com.sporty.group.challenge.event.action;

import com.sporty.group.challenge.event.action.domain.SportEventOutcome;
import com.sporty.group.challenge.event.action.domain.SportEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PublishSportEvent {

    private final SportEventPublisher publisher;

    public PublishSportEvent(SportEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(String eventId, String eventName, String eventWinnerId) {
        publisher.publish(new SportEventOutcome(eventId, eventName, eventWinnerId));
    }
}
