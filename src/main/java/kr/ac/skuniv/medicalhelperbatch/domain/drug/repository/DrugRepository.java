package kr.ac.skuniv.medicalhelperbatch.domain.drug.repository;

import kr.ac.skuniv.medicalhelperbatch.domain.drug.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByBreakfast(boolean breakfast);
    List<Drug> findByLunch(boolean lunch);
    List<Drug> findByDinner(boolean dinner);
}

