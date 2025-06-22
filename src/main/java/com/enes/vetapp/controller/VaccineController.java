package com.enes.vetapp.controller;

import com.enes.vetapp.entity.Vaccine;
import com.enes.vetapp.service.VaccineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping("/{animalId}")
    public ResponseEntity<Vaccine> create(@PathVariable Long animalId, @RequestBody Vaccine vaccine) {
        return ResponseEntity.ok(vaccineService.create(animalId, vaccine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> update(@PathVariable Long id, @RequestBody Vaccine vaccine) {
        return ResponseEntity.ok(vaccineService.update(id, vaccine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vaccineService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Vaccine>> getAll() {
        return ResponseEntity.ok(vaccineService.getAll());
    }

    @GetMapping("/by-animal/{animalId}")
    public ResponseEntity<List<Vaccine>> getByAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(vaccineService.getByAnimalId(animalId));
    }

    @GetMapping("/protection-range")
    public ResponseEntity<List<Vaccine>> getByProtectionFinishRange(@RequestParam LocalDate start,
                                                                    @RequestParam LocalDate end) {
        return ResponseEntity.ok(vaccineService.getByProtectionFinishBetween(start, end));
    }
}
