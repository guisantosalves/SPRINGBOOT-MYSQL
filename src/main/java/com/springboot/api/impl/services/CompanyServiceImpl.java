package com.springboot.api.impl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.model.Company;
import com.springboot.api.repository.CompanyRepository;
import com.springboot.api.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
    
    /*
     * Dependency injection
     */
    private CompanyRepository companyRepo;
    
    @Autowired // linking
    public CompanyServiceImpl(CompanyRepository companyRepo){
        this.companyRepo = companyRepo;
    }

    @Override
    public Company saveCompany(Company company){
        return companyRepo.save(company);
    }

    @Override
    public List<Company> getAllCompanies(){
        return companyRepo.findAll();
    }

    @Override
    public void deleteCompanyById(Long id){
        companyRepo.deleteById(id);
    }
}
