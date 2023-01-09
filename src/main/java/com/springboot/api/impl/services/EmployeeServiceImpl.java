package com.springboot.api.impl.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.exception.ResourceNotFoundException;
import com.springboot.api.model.Employee;
import com.springboot.api.repository.EmployeeRepository;
import com.springboot.api.service.EmployeeService;

import jakarta.transaction.Transactional;

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

    @Autowired // linking - dependecy injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        // return the saved item
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeebyid(Long id) {
        Optional<Employee> empp = employeeRepo.findById(id);

        // if find
        if (empp.isPresent()) {
            return empp.get();
        } else {
            throw new ResourceNotFoundException(id.toString(), id.toString(), empp.get());
        }

    }

    @Override
    public void deleteEmployeesById(Long id) {

        Optional<Employee> emplo = employeeRepo.findById(id);

        if(emplo.isPresent()){
            employeeRepo.deleteById(id);
        }else{
            throw new IllegalStateException("Id "+ id +" doesn't exist");
        }
        
        
    }
    
    @Transactional
    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Employee emplo = employeeRepo
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee doesn't exist with " + id + " id"));

        // verifying name
        boolean verifyingName = !Objects.equals(emplo.getFirstName(), employee.getFirstName());
        if (employee.getFirstName() != null && employee.getFirstName().length() > 0 && verifyingName) {
            emplo.setFirstName(employee.getFirstName());
        }

        //verifying last name
        boolean verifyingLName = !Objects.equals(emplo.getLastName(), employee.getLastName());
        if(employee.getLastName() != null && verifyingLName){
            emplo.setLastName(employee.getLastName());
        }
        
        boolean verifyingEmail = !Objects.equals(emplo.getEmail(), employee.getEmail());
        if(employee.getEmail() != null && employee.getEmail().length() > 0 && verifyingEmail){
            emplo.setEmail(employee.getEmail());
        }

        return emplo;
    }
}
