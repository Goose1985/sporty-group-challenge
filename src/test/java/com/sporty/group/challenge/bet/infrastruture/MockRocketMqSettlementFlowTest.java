package com.sporty.group.challenge.bet.infrastruture;

import com.sporty.group.challenge.bet.action.SettleBetSettlementUseCase;
import com.sporty.group.challenge.bet.domain.BetSettlementLedger;
import com.sporty.group.challenge.bet.domain.BetSettlementMessage;
import com.sporty.group.challenge.bet.domain.BetSettlementMessageBus;
import com.sporty.group.challenge.bet.domain.SettlementResult;
import com.sporty.group.challenge.bet.infrastructure.MockRocketMqBetSettlementConsumer;
import com.sporty.group.challenge.bet.infrastructure.MockRocketMqBetSettlementPublisher;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

class MockRocketMqSettlementFlowTest {


    @Test
    void given_a_settlement_message_when_published_then_consumer_settles_and_records_result() {
        // Given
        BetSettlementMessageBus bus = new FakeBus();
        BetSettlementLedger ledger = new FakeLedger();
        var useCase = new SettleBetSettlementUseCase(ledger);

        new MockRocketMqBetSettlementConsumer(bus, useCase);
        var publisher = new MockRocketMqBetSettlementPublisher(bus);

        var message = new BetSettlementMessage(
                "bet-1",
                "user-1",
                "event-100",
                "market-1",
                "winner-10",
                new BigDecimal("50.00"),
                "winner-10"
        );

        // When
        publisher.publish(message);

        // Then
        assertThat(ledger.findByBetId("bet-1")).contains(SettlementResult.WON);
    }

    // --- Fakes (test-only), dependen solo de interfaces ---

    private static final class FakeBus implements BetSettlementMessageBus {
        private final ConcurrentHashMap<String, CopyOnWriteArrayList<Consumer<BetSettlementMessage>>> subs =
                new ConcurrentHashMap<>();

        @Override
        public void subscribe(String topic, Consumer<BetSettlementMessage> handler) {
            subs.computeIfAbsent(topic, __ -> new CopyOnWriteArrayList<>()).add(handler);
        }

        @Override
        public void publish(String topic, BetSettlementMessage message) {
            var handlers = subs.get(topic);
            if (handlers == null) return;
            for (var h : handlers) h.accept(message);
        }
    }

    private static final class FakeLedger implements BetSettlementLedger {
        private final ConcurrentHashMap<String, SettlementResult> store = new ConcurrentHashMap<>();

        @Override
        public void record(String betId, SettlementResult result) {
            store.put(betId, result);
        }

        @Override
        public Optional<SettlementResult> findByBetId(String betId) {
            return Optional.ofNullable(store.get(betId));
        }
    }
}