package br.com.investitrace.investitraceapi.domain.repository;

import br.com.investitrace.investitraceapi.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { }
