package com.producer.contoller;

import com.producer.confing.RabbitMQConfig;
import com.producer.entities.Employee;
import com.producer.event.EmployeeEvent;
import com.producer.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-service")
public class EmpController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
    @PostMapping
    public void  createEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.createUser(employee);
        logger.info("emp : {}",employee1);

        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setId(employee1.getId());
        employeeEvent.setName(employee1.getName());
        employeeEvent.setDepartmentId(employee1.getDepartmentId());
        logger.info("message before sending:{}",employeeEvent);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTING_KEY,employeeEvent);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee1 = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee1, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable  int id,@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.ACCEPTED);
    }
}
