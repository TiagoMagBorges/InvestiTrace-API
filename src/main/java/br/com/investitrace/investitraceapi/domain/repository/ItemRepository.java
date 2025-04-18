package br.com.investitrace.investitraceapi.domain.repository;

import br.com.investitrace.investitraceapi.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> { }
