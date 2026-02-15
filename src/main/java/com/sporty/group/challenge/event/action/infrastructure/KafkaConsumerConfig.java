package com.sporty.group.challenge.event.action.infrastructure;

import com.sporty.group.challenge.event.action.domain.SportEventOutcome;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public DefaultKafkaConsumerFactory<String, SportEventOutcome> eventOutcomeConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(GROUP_ID_CONFIG, "sporty-group-challenge");
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");

        var jsonDeserializer = new JsonDeserializer<>(SportEventOutcome.class);
        jsonDeserializer.addTrustedPackages("com.sporty.group.challenge.event.domain.model");
        jsonDeserializer.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SportEventOutcome> kafkaListenerContainerFactory(
            DefaultKafkaConsumerFactory<String, SportEventOutcome> eventOutcomeConsumerFactory
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, SportEventOutcome>();
        factory.setConsumerFactory(eventOutcomeConsumerFactory);
        return factory;
    }
}
