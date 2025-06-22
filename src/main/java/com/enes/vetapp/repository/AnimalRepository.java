package com.enes.vetapp.repository;

import com.enes.vetapp.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByNameContainingIgnoreCase(String name);
    List<Animal> findByCustomerId(Long customerId);
}
