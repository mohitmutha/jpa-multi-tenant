package com.mm.poc.multitenant.services;

import com.mm.poc.multitenant.entity.Employee;
import com.mm.poc.multitenant.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeService {
  
  @Autowired
  private EmployeeRepository employeeRepository;
  
  
  public Employee save(Employee employee){
    return employeeRepository.save(employee);
  }
  
  public List<Employee> getAll() throws SQLException {
    return employeeRepository.findAll();
    
  }
  
  public Employee get(Long id){
    return employeeRepository.findById(id).get();
  }
  
  public Employee getByName(String name){
    return employeeRepository.findByName(name);
  }
  
  public void delete(Long id){
    employeeRepository.deleteById(id);
  }
}