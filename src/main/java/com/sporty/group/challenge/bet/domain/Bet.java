package com.sporty.group.challenge.bet.domain;

/**
 * Bet in database is composed by:
 * - Bet ID
 * - User ID
 * - Event ID
 * - Event Market ID
 * - Event Winner ID
 * - Bet Amount
 */
public record Bet(
        BetId betId,
        UserId userId,
        EventId eventId,
        EventMarketId eventMarketId,
        WinnerId eventWinnerId,
        BetAmount betAmount
) {}
