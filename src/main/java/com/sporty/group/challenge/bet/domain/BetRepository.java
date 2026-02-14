package com.sporty.group.challenge.bet.domain;

import java.util.List;

public interface BetRepository {
    void save(Bet bet);                 // upsert
    List<Bet> findByEventId(String eventId);
}

