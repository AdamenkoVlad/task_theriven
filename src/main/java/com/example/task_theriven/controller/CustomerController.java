package com.example.task_theriven.controller;

import com.example.task_theriven.model.Customer;
import com.example.task_theriven.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "the Customer Endpoint")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok().body(customerService.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomer(){
        return ResponseEntity.ok().body(customerService.findAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Customer customer = customerService.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updateCustomer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updateCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long id){
            customerService.deleteCustomerById(id);
            return ResponseEntity.ok("Клієнта успішно видалено");
    }

}
