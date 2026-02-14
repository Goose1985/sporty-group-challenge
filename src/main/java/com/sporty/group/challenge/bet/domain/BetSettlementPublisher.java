package com.sporty.group.challenge.bet.domain;

public interface BetSettlementPublisher {
    void publish(BetSettlementMessage message);
}
