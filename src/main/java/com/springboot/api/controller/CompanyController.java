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

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    /*
     * @Component is a class-level annotation, but @Bean is at the method level, so
     * 
     * @Component is only an option when a class's source code is editable.
     * 
     * @Bean can always be used, but it's more verbose. @Component is compatible
     * with Spring's auto-detection,
     * but @Bean requires manual class instantiation.
     */

    // controller -> IService -> Service -> Repository -> model
    private CompanyService companyService;

    @Autowired // dependency injection
    public CompanyController(CompanyService companyService) {
        super();
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return new ResponseEntity<Company>(companyService.saveCompany(company), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<List<Company>>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(path = "{companyId}", method = RequestMethod.DELETE)
    public void deleteCompanyById(@PathVariable("companyId") Long id) {
        companyService.deleteCompanyById(id);
    }

    @RequestMapping(path = "{companyId}", method = RequestMethod.GET)
    public ResponseEntity<Company> getCompanyById(@PathVariable("companyId") Long id) {
        return new ResponseEntity<Company>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "{companyId}", method = RequestMethod.PUT)
    public ResponseEntity<Company> updateCompanyById(@PathVariable("companyId") Long id, @RequestBody Company company) {
        return new ResponseEntity<Company>(companyService.updateCompanyById(id, company), HttpStatus.OK);
    }

}
