package br.com.investitrace.investitraceapi.domain.repository;

import br.com.investitrace.investitraceapi.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
