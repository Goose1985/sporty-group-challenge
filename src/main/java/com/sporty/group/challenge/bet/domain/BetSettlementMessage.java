package com.sporty.group.challenge.bet.domain;

import java.math.BigDecimal;

public record BetSettlementMessage(
        String betId,
        String userId,
        String eventId,
        String eventMarketId,
        String betEventWinnerId,
        BigDecimal betAmount,
        String outcomeEventWinnerId
) {}
