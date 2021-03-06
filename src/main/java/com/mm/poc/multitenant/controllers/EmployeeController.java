package com.mm.poc.multitenant.controllers;

import com.mm.poc.multitenant.entity.Employee;
import com.mm.poc.multitenant.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController("/employees")
public class EmployeeController {
  
  @Autowired
  private EmployeeService employeeService;
  
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<?> save(@RequestBody Employee employee) {
    Employee newEmployee = employeeService.save(employee);
    return new ResponseEntity<>(newEmployee, HttpStatus.OK);
  }
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<List<Employee>> getAll() throws SQLException {
    List<Employee> employees = employeeService.getAll();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Employee> get(@PathVariable(value = "id") Long id) {
    Employee employee = employeeService.get(id);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }
  
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Employee> delete(@PathVariable(value = "id") Long id) {
    employeeService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}


