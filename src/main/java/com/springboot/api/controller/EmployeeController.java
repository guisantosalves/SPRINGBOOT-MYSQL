package com.springboot.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.model.Employee;
import com.springboot.api.service.EmployeeService;

/*
 * controller -> impl.services -> services -> repository -> model(entities)
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    // controller -> IService -> Service -> repository -> model
    // when we get a interface method to represent here in controller
    // automatically the spring boot will get the implementation from the method in the implementated class
    // if I want to get diferent implementation services I get to use @Qualifier
    // -> https://stackoverflow.com/questions/51766013/spring-boot-autowiring-an-interface-with-multiple-implementations
    // -> https://www.devmedia.com.br/java-interface-aprenda-a-usar-corretamente/28798
    // -> https://www.javaprogressivo.net/2012/10/Interface-em-Java-implements-O-que-e-para-que-serve-como-funciona.html
    private EmployeeService EmployeeService;

    @Autowired // linking - dependency injection
    public EmployeeController(EmployeeService employeeService){
        super();
        this.EmployeeService = employeeService;
    }

    //create employee
    // executed by service... that's why using impl method
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(EmployeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //get all employees
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<List<Employee>>(EmployeeService.getAllEmployees(), HttpStatus.ACCEPTED);
    }

    // path - pathVariable
    //get by id
    @RequestMapping(path = "{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeebyid(@PathVariable("employeeId") Long id){
        return new ResponseEntity<Employee>(EmployeeService.getEmployeebyid(id), HttpStatus.ACCEPTED);
    }
}
