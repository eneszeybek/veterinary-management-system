package com.enes.vetapp.repository;

import com.enes.vetapp.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate start, LocalDate end);
    boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId, String name, String code, LocalDate date);
}
