package com.sporty.group.challenge.bet.infrastruture;

import com.sporty.group.challenge.bet.domain.Bet;
import com.sporty.group.challenge.bet.domain.BetRepository;
import com.sporty.group.challenge.bet.domain.EventId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class InMemoryBetRepository implements BetRepository {

    private final CopyOnWriteArrayList<Bet> store = new CopyOnWriteArrayList<>();

    @Override
    public void save(Bet bet) {
        store.removeIf(existing -> existing.betId().value().equals(bet.betId().value()));
        store.add(bet);
    }

    @Override
    public List<Bet> findByEventId(String eventId) {
        return store.stream()
                .filter(b -> b.eventId().value().equals(new EventId(eventId).value()))
                .toList();
    }

}
