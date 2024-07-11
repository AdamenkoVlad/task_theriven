package com.example.task_theriven.repository;

import com.example.task_theriven.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findByIsActiveTrue();

}
