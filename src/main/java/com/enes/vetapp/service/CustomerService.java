package com.enes.vetapp.service;

import com.enes.vetapp.entity.Customer;
import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer update(Long id, Customer customer);
    void delete(Long id);
    Customer getById(Long id);
    List<Customer> getAll();
    List<Customer> getByName(String name);
}
