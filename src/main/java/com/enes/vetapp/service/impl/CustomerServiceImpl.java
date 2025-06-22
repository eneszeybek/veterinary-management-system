package com.enes.vetapp.service.impl;

import com.enes.vetapp.entity.Customer;
import com.enes.vetapp.exception.BadRequestException;
import com.enes.vetapp.exception.ResourceNotFoundException;
import com.enes.vetapp.repository.CustomerRepository;
import com.enes.vetapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        if (customerRepository.existsByMail(customer.getMail())) {
            throw new BadRequestException("Kayıt sistemde mevcut.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id’li kayıt sistemde bulunamadı."));
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id’li kayıt sistemde bulunamadı."));
        customerRepository.delete(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id’li kayıt sistemde bulunamadı."));
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
}
