package com.springboot.api.service;

import java.util.List;

import com.springboot.api.model.Company;

public interface CompanyService {
    Company saveCompany(Company company);

    List<Company> getAllCompanies();
    
    void deleteCompanyById(Long id);

    Company getCompanyById(Long id);

    Company updateCompanyById(Long id, Company company);
}
