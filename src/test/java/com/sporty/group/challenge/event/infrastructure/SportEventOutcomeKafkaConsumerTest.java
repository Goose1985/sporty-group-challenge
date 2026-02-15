package com.sporty.group.challenge.event.infrastructure;

import com.sporty.group.challenge.bet.domain.*;
import com.sporty.group.challenge.bet.infrastructure.EventOutcomeKafkaConsumer;
import com.sporty.group.challenge.bet.infrastructure.InMemoryBetRepository;
import com.sporty.group.challenge.event.action.domain.SportEventOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class SportEventOutcomeKafkaConsumerTest {

    private BetRepository betRepository;

    @BeforeEach
    public void init(){
        betRepository = new InMemoryBetRepository();
    }

    @Test
    void when_outcome_arrives_then_publishes_settlements_for_matching_event_bets() {
        betRepository.save(new Bet(
                new BetId("bet-1"),
                new UserId("user-1"),
                new EventId("event-100"),
                new EventMarketId("market-1"),
                new WinnerId("winner-10"),
                new BetAmount(new BigDecimal("50.00"))
        ));
        betRepository.save(new Bet(
                new BetId("bet-2"),
                new UserId("user-2"),
                new EventId("event-200"),
                new EventMarketId("market-2"),
                new WinnerId("winner-20"),
                new BetAmount(new BigDecimal("20.00"))
        ));

        var published = new ArrayList<BetSettlementMessage>();
        BetSettlementPublisher publisher = published::add;

        var consumer = new EventOutcomeKafkaConsumer(betRepository, publisher);

        consumer.onMessage(new SportEventOutcome("event-100", "A vs B", "winner-10"));

        assertThat(published).hasSize(1);
        assertThat(published.get(0).betId()).isEqualTo("bet-1");
        assertThat(published.get(0).eventId()).isEqualTo("event-100");
        assertThat(published.get(0).outcomeEventWinnerId()).isEqualTo("winner-10");
    }

    @Test
    void when_no_matching_bets_then_publishes_nothing() {
        BetRepository repo = new InMemoryBetRepository();
        var published = new ArrayList<BetSettlementMessage>();
        BetSettlementPublisher publisher = published::add;

        var consumer = new EventOutcomeKafkaConsumer(repo, publisher);

        consumer.onMessage(new SportEventOutcome("event-x", "X vs Y", "winner-x"));

        assertThat(published).isEmpty();
    }
}
