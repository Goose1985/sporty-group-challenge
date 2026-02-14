package com.sporty.group.challenge.bet.domain;


import java.util.function.Consumer;

public interface BetSettlementMessageBus {
    void subscribe(String topic, Consumer<BetSettlementMessage> handler);
    void publish(String topic, BetSettlementMessage message);
}
