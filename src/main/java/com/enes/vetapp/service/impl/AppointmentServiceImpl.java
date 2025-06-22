package com.enes.vetapp.service.impl;

import com.enes.vetapp.entity.Animal;
import com.enes.vetapp.entity.Appointment;
import com.enes.vetapp.entity.Doctor;
import com.enes.vetapp.exception.BadRequestException;
import com.enes.vetapp.exception.ResourceNotFoundException;
import com.enes.vetapp.repository.AnimalRepository;
import com.enes.vetapp.repository.AppointmentRepository;
import com.enes.vetapp.repository.AvailableDateRepository;
import com.enes.vetapp.repository.DoctorRepository;
import com.enes.vetapp.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final AvailableDateRepository availableDateRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  DoctorRepository doctorRepository,
                                  AnimalRepository animalRepository,
                                  AvailableDateRepository availableDateRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public Appointment create(Long doctorId, Long animalId, Appointment appointment) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));

        LocalDate appointmentDateOnly = appointment.getAppointmentDate().toLocalDate();

        boolean isAvailable = availableDateRepository.existsByDoctorIdAndAvailableDate(doctorId, appointmentDateOnly);
        if (!isAvailable) {
            throw new BadRequestException("Doktor bu tarihte çalışmamaktadır.");
        }

        boolean conflict = appointmentRepository.existsByDoctorIdAndAppointmentDate(doctorId, appointment.getAppointmentDate());
        if (conflict) {
            throw new BadRequestException("Girilen saatte başka bir randevu mevcuttur.");
        }

        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Long id, Appointment appointment) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setId(id);
        appointment.setDoctor(existing.getDoctor());
        appointment.setAnimal(existing.getAnimal());
        return appointmentRepository.save(appointment);
    }

    @Override
    public void delete(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, start, end);
    }

    @Override
    public List<Appointment> getByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId, start, end);
    }
}
