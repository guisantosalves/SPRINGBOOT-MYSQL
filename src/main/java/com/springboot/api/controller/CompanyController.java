package com.springboot.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.model.Company;
import com.springboot.api.service.CompanyService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    
    // controller -> IService -> Service -> Repository -> model
    private CompanyService companyService;

    @Autowired // dependency injection
    public CompanyController(CompanyService companyService){
        super();
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Company> saveCompany(@RequestBody Company company){
        return new ResponseEntity<Company>(companyService.saveCompany(company), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<List<Company>>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(path = "{companyId}",method = RequestMethod.DELETE)
    public void deleteCompanyById(@PathVariable("companyId") Long id){
        companyService.deleteCompanyById(id);
    }
}
