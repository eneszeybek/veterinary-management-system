package com.enes.vetapp.repository;

import com.enes.vetapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByMail(String mail);
}
