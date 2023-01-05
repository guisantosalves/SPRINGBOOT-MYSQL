package com.springboot.api.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.model.Employee;
import com.springboot.api.repository.EmployeeRepository;
import com.springboot.api.service.EmployeeService;

// implements -> interface
// extends -> class

// impl ---> service(interfaces)

@Service
public class EmployeeServiceImpl implements EmployeeService {
    /*
     * setter-based dependency injection
     * Constructor-based dependency injection
     */

    private EmployeeRepository employeeRepo;

    @Autowired //linking
    public EmployeeServiceImpl(EmployeeRepository employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee){

        // return the saved item
        return employeeRepo.save(employee);
    }
    
}
