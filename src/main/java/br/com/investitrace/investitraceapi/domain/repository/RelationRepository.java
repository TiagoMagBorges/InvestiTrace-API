package br.com.investitrace.investitraceapi.domain.repository;

import br.com.investitrace.investitraceapi.domain.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> { }