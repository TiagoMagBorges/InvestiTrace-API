package br.com.investitrace.investitraceapi.service.impl;

import br.com.investitrace.investitraceapi.domain.model.Person;
import br.com.investitrace.investitraceapi.domain.repository.PersonRepository;
import br.com.investitrace.investitraceapi.domain.repository.UserRepository;
import br.com.investitrace.investitraceapi.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final UserRepository userRepository;

    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Person createPerson(Person person) {
        validatePersonDoesNotExist(person);
        validateUserExists(person.getUserId());
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        Person existingPerson = getPersonOrThrow(person.getId());

        if (!existingPerson.getUserId().equals(person.getUserId())) {
            throw new IllegalArgumentException("UserId mismatch. Cannot update person with a different user.");
        }

        existingPerson.setName(person.getName());
        existingPerson.setDescription(person.getDescription());
        existingPerson.setImageUrl(person.getImageUrl());

        personRepository.save(existingPerson);
    }

    @Override
    @Transactional
    public void deletePerson(Long personId, Long userId) {
        if(!personRepository.existsById(personId)) {
            throw new EntityNotFoundException("Person not found with id: " + personId);
        }
        if(!userRepository.existsById(personId)) {
            throw new EntityNotFoundException("User not found with id: " + personId);
        }
        personRepository.deleteById(personId);
    }

    @Override
    public Person getPersonById(Long personId) {
        return getPersonOrThrow(personId);
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    private void validatePersonDoesNotExist(Person person) {
        if (person.getId() != null && personRepository.existsById(person.getId())) {
            throw new IllegalArgumentException("Person already exists with id: " + person.getId());
        }
    }

    private Person getPersonOrThrow(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
    }

    private void validateUserExists(Long userId) {
        if (userId == null || !userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
    }
}
