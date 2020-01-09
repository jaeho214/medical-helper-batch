package kr.ac.skuniv.medicalhelperbatch.domain.treatment.repository;

import kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity.Drug;
import kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    Optional<Treatment> findByDrug(Drug drug);
}
