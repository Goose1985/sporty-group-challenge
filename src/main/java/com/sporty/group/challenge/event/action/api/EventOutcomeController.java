package com.sporty.group.challenge.event.action.api;

import com.sporty.group.challenge.event.action.PublishEventOutcomeUseCase;
import com.sporty.group.challenge.event.action.domain.EventOutcome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event-outcomes")
public class EventOutcomeController {

    private final PublishEventOutcomeUseCase useCase;

    public EventOutcomeController(PublishEventOutcomeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Void> publish(@RequestBody PublishEventOutcomeRequest request) {
        useCase.publish(
                request.eventId(),
                request.eventName(),
                request.eventWinnerId()
        );
        return ResponseEntity.accepted().build();
    }
}
