package com.enes.vetapp.service;

import com.enes.vetapp.entity.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment create(Long doctorId, Long animalId, Appointment appointment);
    Appointment update(Long id, Appointment appointment);
    void delete(Long id);
    Appointment getById(Long id);
    List<Appointment> getAll();
    List<Appointment> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end);
    List<Appointment> getByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end);
}
