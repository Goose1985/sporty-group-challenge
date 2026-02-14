package com.sporty.group.challenge.bet.infrastruture;

import com.sporty.group.challenge.bet.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BetSeedData implements CommandLineRunner {

    private final BetRepository betRepository;

    public BetSeedData(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Override
    public void run(String... args) {
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
                new EventId("event-100"),
                new EventMarketId("market-1"),
                new WinnerId("winner-11"),
                new BetAmount(new BigDecimal("20.00"))
        ));

        betRepository.save(new Bet(
                new BetId("bet-3"),
                new UserId("user-3"),
                new EventId("event-200"),
                new EventMarketId("market-2"),
                new WinnerId("winner-20"),
                new BetAmount(new BigDecimal("10.00"))
        ));
    }
}
