package com.sporty.group.challenge.event.action.domain;

/**
 * Modelo de dominio: el outcome de un evento deportivo.
 */
public record EventOutcome(
        String eventId,
        String eventName,
        String eventWinnerId
) {}
