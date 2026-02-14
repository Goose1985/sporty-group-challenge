package com.sporty.group.challenge.bet.domain;

import java.util.Optional;

public interface BetSettlementLedger {
    void record(String betId, SettlementResult result);
    Optional<SettlementResult> findByBetId(String betId);
}
