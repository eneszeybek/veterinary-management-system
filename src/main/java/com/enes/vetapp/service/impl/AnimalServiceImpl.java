package com.enes.vetapp.service.impl;

import com.enes.vetapp.entity.Animal;
import com.enes.vetapp.entity.Customer;
import com.enes.vetapp.exception.ResourceNotFoundException;
import com.enes.vetapp.repository.AnimalRepository;
import com.enes.vetapp.repository.CustomerRepository;
import com.enes.vetapp.service.AnimalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, CustomerRepository customerRepository) {
        this.animalRepository = animalRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Animal create(Long customerId, Animal animal) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        animal.setCustomer(customer);
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(Long id, Animal animal) {
        Animal existing = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
        animal.setId(id);
        animal.setCustomer(existing.getCustomer());
        return animalRepository.save(animal);
    }

    @Override
    public void delete(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
        animalRepository.delete(animal);
    }

    @Override
    public Animal getById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
    }

    @Override
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @Override
    public List<Animal> getByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Animal> getByCustomerId(Long customerId) {
        return animalRepository.findByCustomerId(customerId);
    }
}
