package com.sporty.group.challenge.bet.api;

import com.sporty.group.challenge.bet.api.response.BetView;
import com.sporty.group.challenge.bet.domain.BetRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BetQueryController {

    private final BetRepository betRepository;

    public BetQueryController(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @GetMapping("/bets")
    public List<BetView> list(@RequestParam(name = "eventId", required = false) String eventId) {
        if (Strings.isBlank(eventId)) {
            throw new IllegalArgumentException("Query parameter 'eventId' is required.");
        }

        var bets = betRepository.findByEventId(eventId);

        return bets.stream()
                .map(b -> new BetView(
                        b.betId().value(),
                        b.userId().value(),
                        b.eventId().value(),
                        b.eventMarketId().value(),
                        b.eventWinnerId().value(),
                        b.betAmount().value()
                ))
                .toList();
    }
}
