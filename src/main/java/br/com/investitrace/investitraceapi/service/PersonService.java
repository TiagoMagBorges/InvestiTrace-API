package br.com.investitrace.investitraceapi.service;

import br.com.investitrace.investitraceapi.domain.model.Person;

import java.util.List;

public interface PersonService {
    Person createPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long personId, Long userId);

    Person getPersonById(Long personId);

    List<Person> getAllPeople();
}
