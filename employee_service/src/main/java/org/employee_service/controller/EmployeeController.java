package org.employee_service.controller;

import org.employee_service.model.Employee;
import org.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee> saveUser(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveUser(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Employee> getUser(@PathVariable int userId) {
        Employee employee = employeeService.getUser(userId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
