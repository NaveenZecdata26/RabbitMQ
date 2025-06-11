package com.producer.service;

import com.producer.entities.Employee;

public interface EmployeeService {
    public Employee createUser(Employee employee);

    public Employee getEmployeeById(int id);

    public Employee updateEmployee(int id, Employee employee);
}