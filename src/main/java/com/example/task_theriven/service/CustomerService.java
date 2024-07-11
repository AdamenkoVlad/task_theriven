package com.example.task_theriven.service;

import com.example.task_theriven.exception.CustomerNotFoundException;
import com.example.task_theriven.model.Customer;
import com.example.task_theriven.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findByIsActiveTrue();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Не знайдений клієнт по id : " + id));
    }

    public Customer updateCustomer(Long id, Customer updateCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Не знайдений клієнт по id : " + id));

        customer.setFullName(updateCustomer.getFullName());
        customer.setPhone(updateCustomer.getPhone());
        customer.setUpdated(System.currentTimeMillis());
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Не знайдений клієнт по id : " + id));
        customer.setActive(false);
        customerRepository.deleteById(id);
    }

}
