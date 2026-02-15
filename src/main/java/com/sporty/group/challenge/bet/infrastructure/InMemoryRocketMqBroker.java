package com.sporty.group.challenge.bet.infrastructure;

import com.sporty.group.challenge.bet.domain.BetSettlementMessage;
import com.sporty.group.challenge.bet.domain.BetSettlementMessageBus;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Component
public class InMemoryRocketMqBroker implements BetSettlementMessageBus {

    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Consumer<BetSettlementMessage>>> subscribers =
            new ConcurrentHashMap<>();

    @Override
    public void subscribe(String topic, Consumer<BetSettlementMessage> handler) {
        subscribers.computeIfAbsent(topic, __ -> new CopyOnWriteArrayList<>()).add(handler);
    }

    @Override
    public void publish(String topic, BetSettlementMessage message) {
        var handlers = subscribers.get(topic);
        if (handlers == null) return;
        for (var handler : handlers) handler.accept(message);
    }
}
