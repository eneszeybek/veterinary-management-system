package com.enes.vetapp.controller;

import com.enes.vetapp.entity.AvailableDate;
import com.enes.vetapp.service.AvailableDateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available-dates")
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping("/{doctorId}")
    public ResponseEntity<AvailableDate> create(@PathVariable Long doctorId, @RequestBody AvailableDate availableDate) {
        return ResponseEntity.ok(availableDateService.create(doctorId, availableDate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDate> update(@PathVariable Long id, @RequestBody AvailableDate availableDate) {
        return ResponseEntity.ok(availableDateService.update(id, availableDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        availableDateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableDate> getById(@PathVariable Long id) {
        return ResponseEntity.ok(availableDateService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AvailableDate>> getAll() {
        return ResponseEntity.ok(availableDateService.getAll());
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<AvailableDate>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(availableDateService.getByDoctorId(doctorId));
    }
}
