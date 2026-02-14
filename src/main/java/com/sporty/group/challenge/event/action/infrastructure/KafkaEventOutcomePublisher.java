package com.sporty.group.challenge.event.action.infrastructure;

import com.sporty.group.challenge.event.action.domain.EventOutcome;
import com.sporty.group.challenge.event.action.domain.EventOutcomePublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventOutcomePublisher implements EventOutcomePublisher {

    private static final String TOPIC = "event-outcomes";

    private final KafkaTemplate<String, EventOutcome> kafkaTemplate;

    public KafkaEventOutcomePublisher(KafkaTemplate<String, EventOutcome> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(EventOutcome eventOutcome) {
        kafkaTemplate.send(TOPIC, eventOutcome.eventId(), eventOutcome);
    }
}
