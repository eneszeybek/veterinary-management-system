package com.enes.vetapp.service.impl;

import com.enes.vetapp.entity.Animal;
import com.enes.vetapp.entity.Vaccine;
import com.enes.vetapp.exception.BadRequestException;
import com.enes.vetapp.exception.ResourceNotFoundException;
import com.enes.vetapp.repository.AnimalRepository;
import com.enes.vetapp.repository.VaccineRepository;
import com.enes.vetapp.service.VaccineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    public VaccineServiceImpl(VaccineRepository vaccineRepository, AnimalRepository animalRepository) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Vaccine create(Long animalId, Vaccine vaccine) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
        boolean exists = vaccineRepository.existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(
                animalId, vaccine.getName(), vaccine.getCode(), LocalDate.now());
        if (exists) {
            throw new BadRequestException("Bu aşının koruyuculuğu henüz bitmemiş.");
        }
        vaccine.setAnimal(animal);
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine update(Long id, Vaccine vaccine) {
        Vaccine existing = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));
        vaccine.setId(id);
        vaccine.setAnimal(existing.getAnimal());
        return vaccineRepository.save(vaccine);
    }

    @Override
    public void delete(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));
        vaccineRepository.delete(vaccine);
    }

    @Override
    public Vaccine getById(Long id) {
        return vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));
    }

    @Override
    public List<Vaccine> getAll() {
        return vaccineRepository.findAll();
    }

    @Override
    public List<Vaccine> getByAnimalId(Long animalId) {
        return vaccineRepository.findByAnimalId(animalId);
    }

    @Override
    public List<Vaccine> getByProtectionFinishBetween(LocalDate start, LocalDate end) {
        return vaccineRepository.findByProtectionFinishDateBetween(start, end);
    }
}
