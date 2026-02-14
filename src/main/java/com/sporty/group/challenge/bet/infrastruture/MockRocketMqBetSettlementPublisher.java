package com.sporty.group.challenge.bet.infrastruture;

import com.sporty.group.challenge.bet.domain.BetSettlementMessage;
import com.sporty.group.challenge.bet.domain.BetSettlementPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockRocketMqBetSettlementPublisher implements BetSettlementPublisher {

    private static final Logger log = LoggerFactory.getLogger(MockRocketMqBetSettlementPublisher.class);
    private static final String TOPIC = "bet-settlements";

    @Override
    public void publish(BetSettlementMessage message) {
        log.info("Mock RocketMQ publish to topic='{}' payload={}", TOPIC, message);
    }
}
