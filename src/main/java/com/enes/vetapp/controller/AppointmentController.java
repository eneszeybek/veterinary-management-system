package com.enes.vetapp.controller;

import com.enes.vetapp.entity.Appointment;
import com.enes.vetapp.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/{doctorId}/{animalId}")
    public ResponseEntity<Appointment> create(@PathVariable Long doctorId, @PathVariable Long animalId, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.create(doctorId, animalId, appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.update(id, appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/by-doctor")
    public ResponseEntity<List<Appointment>> getByDoctorAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(appointmentService.getByDoctorIdAndDateRange(doctorId, start, end));
    }

    @GetMapping("/by-animal")
    public ResponseEntity<List<Appointment>> getByAnimalAndDateRange(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(appointmentService.getByAnimalIdAndDateRange(animalId, start, end));
    }
}
