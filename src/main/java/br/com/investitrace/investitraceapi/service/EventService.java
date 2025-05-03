package br.com.investitrace.investitraceapi.service;

import br.com.investitrace.investitraceapi.domain.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Long eventId, Long userId);

    Event getEventById(Long eventId);

    List<Event> getAllEvents();
}
