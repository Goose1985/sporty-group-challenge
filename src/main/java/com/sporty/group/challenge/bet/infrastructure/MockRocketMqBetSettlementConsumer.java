package com.sporty.group.challenge.bet.infrastructure;

import com.sporty.group.challenge.bet.action.SettleBetSettlementUseCase;
import com.sporty.group.challenge.bet.domain.BetSettlementMessageBus;
import org.springframework.stereotype.Component;

@Component
public class MockRocketMqBetSettlementConsumer {

    private static final String TOPIC = "bet-settlements";

    public MockRocketMqBetSettlementConsumer(
            BetSettlementMessageBus bus,
            SettleBetSettlementUseCase useCase
    ) {
        bus.subscribe(TOPIC, useCase::settle);
    }
}
