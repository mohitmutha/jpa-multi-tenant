package com.mm.poc.multitenant.repository;

import com.mm.poc.multitenant.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
  
  Employee findByName(String name);
  
  void deleteByName(String name);
}