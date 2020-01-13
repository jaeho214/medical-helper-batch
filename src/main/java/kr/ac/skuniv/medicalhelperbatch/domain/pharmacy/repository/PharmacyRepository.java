package kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.repository;

import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
