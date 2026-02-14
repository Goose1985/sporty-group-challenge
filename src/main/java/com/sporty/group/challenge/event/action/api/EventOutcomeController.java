package com.sporty.group.challenge.event.action.api;

import com.sporty.group.challenge.event.action.PublishEventOutcomeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
