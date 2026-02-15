package com.sporty.group.challenge.bet.infrastructure;

import com.sporty.group.challenge.bet.domain.BetSettlementLedger;
import com.sporty.group.challenge.bet.domain.SettlementResult;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryBetSettlementLedger implements BetSettlementLedger {

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
