package com.sporty.group.challenge.bet.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record BetAmount(BigDecimal value) {
    public BetAmount {
        if (value == null) throw new IllegalArgumentException("BetAmount is required");
        if (value.signum() <= 0) throw new IllegalArgumentException("BetAmount must be > 0");
        value = value.setScale(2, RoundingMode.HALF_UP);
    }
}
