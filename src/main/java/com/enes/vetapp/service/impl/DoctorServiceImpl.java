package com.enes.vetapp.service.impl;

import com.enes.vetapp.entity.Doctor;
import com.enes.vetapp.exception.BadRequestException;
import com.enes.vetapp.exception.ResourceNotFoundException;
import com.enes.vetapp.repository.DoctorRepository;
import com.enes.vetapp.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor create(Doctor doctor) {
        if (doctorRepository.existsByMail(doctor.getMail())) {
            throw new BadRequestException("Kayıt sistemde mevcut.");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Long id, Doctor doctor) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id’li kayıt sistemde bulunamadı."));
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id’li kayıt sistemde bulunamadı."));
        doctorRepository.delete(doctor);
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id’li kayıt sistemde bulunamadı."));
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }
}
