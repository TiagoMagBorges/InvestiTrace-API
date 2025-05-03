package br.com.investitrace.investitraceapi.service.impl;

import br.com.investitrace.investitraceapi.domain.model.Event;
import br.com.investitrace.investitraceapi.domain.repository.EventRepository;
import br.com.investitrace.investitraceapi.domain.repository.UserRepository;
import br.com.investitrace.investitraceapi.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        validateEventDoesNotExist(event);
        validateUserExists(event.getUserId());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public void updateEvent(Event event) {
        Event existingEvent = getEventOrThrow(event.getId());

        if (!existingEvent.getUserId().equals(event.getUserId()))
            throw new IllegalArgumentException("UserId mismatch. Cannot update event with a different user.");


    }

    @Override
    @Transactional
    public void deleteEvent(Long eventId, Long userId) {

    }

    @Override
    @Transactional
    public Event getEventById(Long eventId) {
        return null;
    }

    @Override
    @Transactional
    public List<Event> getAllEvents() {
        return List.of();
    }

    private void validateEventDoesNotExist(Event event) {
        if (eventRepository.existsById(event.getId()))
            throw new IllegalArgumentException("Event already exists with id: " + event.getId());
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId))
            throw new IllegalArgumentException("User not found with id: " + userId);
    }

    private Event getEventOrThrow(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));
    }
}
