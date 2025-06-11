package com.producer.service.impl;

import com.producer.entities.Employee;
import com.producer.repositories.EmployeeRepo;
import com.producer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public Employee createUser(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("employee not found with id" + id));
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {

        Employee oldEmployee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("employee not found with id" + id));

        oldEmployee.setName(employee.getName());
        oldEmployee.setDepartmentId(employee.getDepartmentId());
        return oldEmployee;
    }
}
