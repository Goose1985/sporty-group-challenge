package com.sporty.group.challenge.bet.api.response;

import java.math.BigDecimal;

public record BetView(
        String betId,
        String userId,
        String eventId,
        String eventMarketId,
        String eventWinnerId,
        BigDecimal betAmount
) {}
