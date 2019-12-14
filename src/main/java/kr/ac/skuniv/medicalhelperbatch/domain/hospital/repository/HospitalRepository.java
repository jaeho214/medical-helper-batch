package kr.ac.skuniv.medicalhelperbatch.domain.hospital.repository;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
