package com.enes.vetapp.service;

import com.enes.vetapp.entity.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor create(Doctor doctor);
    Doctor update(Long id, Doctor doctor);
    void delete(Long id);
    Doctor getById(Long id);
    List<Doctor> getAll();
}
