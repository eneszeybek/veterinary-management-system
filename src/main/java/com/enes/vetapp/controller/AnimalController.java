package com.enes.vetapp.controller;

import com.enes.vetapp.entity.Animal;
import com.enes.vetapp.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Animal> create(@PathVariable Long customerId, @RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.create(customerId, animal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable Long id, @RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.update(id, animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAll() {
        return ResponseEntity.ok(animalService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Animal>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(animalService.getByName(name));
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<Animal>> getByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(animalService.getByCustomerId(customerId));
    }
}
