package com.springboot.api.impl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.exception.ResourceNotFoundException;
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

    @Autowired //linking - dependecy injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        
        // return the saved item
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeebyid(Long id){
        Optional<Employee> empp = employeeRepo.findById(id);

        // if find
        if(empp.isPresent()){
            return empp.get();    
        }else{
            throw new ResourceNotFoundException(id.toString(), id.toString(), empp.get());
        }
         
    }

    @Override
    public void deleteEmployeesById(Long id){

        // verificar se existe o ID no banco antes
        employeeRepo.deleteById(id);
    }
}
