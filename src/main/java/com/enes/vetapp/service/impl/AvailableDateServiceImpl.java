package com.enes.vetapp.service.impl;

import com.enes.vetapp.entity.AvailableDate;
import com.enes.vetapp.entity.Doctor;
import com.enes.vetapp.exception.ResourceNotFoundException;
import com.enes.vetapp.repository.AvailableDateRepository;
import com.enes.vetapp.repository.DoctorRepository;
import com.enes.vetapp.service.AvailableDateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableDateServiceImpl implements AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;

    public AvailableDateServiceImpl(AvailableDateRepository availableDateRepository, DoctorRepository doctorRepository) {
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public AvailableDate create(Long doctorId, AvailableDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        date.setDoctor(doctor);
        return availableDateRepository.save(date);
    }

    @Override
    public AvailableDate update(Long id, AvailableDate date) {
        AvailableDate existing = availableDateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AvailableDate not found"));
        date.setId(id);
        date.setDoctor(existing.getDoctor());
        return availableDateRepository.save(date);
    }

    @Override
    public void delete(Long id) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AvailableDate not found"));
        availableDateRepository.delete(availableDate);
    }

    @Override
    public AvailableDate getById(Long id) {
        return availableDateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AvailableDate not found"));
    }

    @Override
    public List<AvailableDate> getAll() {
        return availableDateRepository.findAll();
    }

    @Override
    public List<AvailableDate> getByDoctorId(Long doctorId) {
        return availableDateRepository.findByDoctorId(doctorId);
    }
}
