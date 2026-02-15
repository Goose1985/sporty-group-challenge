package com.sporty.group.challenge.bet.infrastruture;

import com.sporty.group.challenge.bet.domain.BetRepository;
import com.sporty.group.challenge.bet.domain.BetSettlementMessage;
import com.sporty.group.challenge.bet.domain.BetSettlementPublisher;
import com.sporty.group.challenge.event.action.domain.SportEventOutcome;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventOutcomeKafkaConsumer {

    private final BetRepository betRepository;
    private final BetSettlementPublisher settlementPublisher;

    public EventOutcomeKafkaConsumer(BetRepository betRepository, BetSettlementPublisher settlementPublisher) {
        this.betRepository = betRepository;
        this.settlementPublisher = settlementPublisher;
    }

    @KafkaListener(topics = "event-outcomes", groupId = "sporty-group-challenge")
    public void onMessage(SportEventOutcome outcome) {
        var bets = betRepository.findByEventId(outcome.eventId());

        for (var bet : bets) {
            var msg = new BetSettlementMessage(
                    bet.betId().value(),
                    bet.userId().value(),
                    bet.eventId().value(),
                    bet.eventMarketId().value(),
                    bet.eventWinnerId().value(),
                    bet.betAmount().value(),
                    outcome.eventWinnerId()
            );
            settlementPublisher.publish(msg);
        }
    }
}
