package kr.ac.skuniv.medicalhelperbatch.domain.drugstore.repository;

import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.entity.Drugstore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugstoreRepository extends JpaRepository<Drugstore, Long> {
}
