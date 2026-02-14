package com.sporty.group.challenge.bet.action;

import com.sporty.group.challenge.bet.domain.BetSettlementLedger;
import com.sporty.group.challenge.bet.domain.BetSettlementMessage;
import com.sporty.group.challenge.bet.domain.SettlementResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SettleBetSettlementUseCase {

    private static final Logger log = LoggerFactory.getLogger(SettleBetSettlementUseCase.class);

    private final BetSettlementLedger ledger;

    public SettleBetSettlementUseCase(BetSettlementLedger ledger) {
        this.ledger = ledger;
    }

    public SettlementResult settle(BetSettlementMessage message) {
        var result = message.betEventWinnerId().equals(message.outcomeEventWinnerId())
                ? SettlementResult.WON
                : SettlementResult.LOST;

        ledger.record(message.betId(), result);

        log.info("Settled betId={} result={} eventId={} betWinner={} outcomeWinner={}",
                message.betId(), result, message.eventId(), message.betEventWinnerId(), message.outcomeEventWinnerId());

        return result;
    }
}
