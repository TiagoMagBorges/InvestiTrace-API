package br.com.investitrace.investitraceapi.service.impl;

import br.com.investitrace.investitraceapi.domain.model.Person;
import br.com.investitrace.investitraceapi.domain.repository.PersonRepository;
import br.com.investitrace.investitraceapi.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public Person createPerson(Person person) {
        validatePersonDoesNotExist(person);
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        Person existingPerson = getPersonOrThrow(person.getId());

        existingPerson.setName(person.getName());
        existingPerson.setDescription(person.getDescription());
        existingPerson.setImageUrl(person.getImageUrl());

        personRepository.save(existingPerson);
    }

    @Override
    @Transactional
    public void deletePerson(Long personId) {
        if(!personRepository.existsById(personId)) {
            throw new EntityNotFoundException("Person not found with id: " + personId);
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
}
