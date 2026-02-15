package com.sporty.group.challenge.event.action.infrastructure;

import com.sporty.group.challenge.event.action.domain.SportEventOutcome;
import com.sporty.group.challenge.event.action.domain.SportEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSportEventPublisher implements SportEventPublisher {

    private static final String TOPIC = "event-outcomes";

    private final KafkaTemplate<String, SportEventOutcome> kafkaTemplate;

    public KafkaSportEventPublisher(KafkaTemplate<String, SportEventOutcome> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(SportEventOutcome sportEventOutcome) {
        kafkaTemplate.send(TOPIC, sportEventOutcome.eventId(), sportEventOutcome);
    }
}
