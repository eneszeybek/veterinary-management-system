package com.enes.vetapp.service;

import com.enes.vetapp.entity.AvailableDate;
import java.util.List;

public interface AvailableDateService {
    AvailableDate create(Long doctorId, AvailableDate date);
    AvailableDate update(Long id, AvailableDate date);
    void delete(Long id);
    AvailableDate getById(Long id);
    List<AvailableDate> getAll();
    List<AvailableDate> getByDoctorId(Long doctorId);
}
