package com.enes.vetapp.service;

import com.enes.vetapp.entity.Vaccine;
import java.time.LocalDate;
import java.util.List;

public interface VaccineService {
    Vaccine create(Long animalId, Vaccine vaccine);
    Vaccine update(Long id, Vaccine vaccine);
    void delete(Long id);
    Vaccine getById(Long id);
    List<Vaccine> getAll();
    List<Vaccine> getByAnimalId(Long animalId);
    List<Vaccine> getByProtectionFinishBetween(LocalDate start, LocalDate end);
}
