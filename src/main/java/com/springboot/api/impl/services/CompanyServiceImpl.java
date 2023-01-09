package com.springboot.api.impl.services;

import java.util.List;
import java.util.Objects;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.model.Company;
import com.springboot.api.model.Employee;
import com.springboot.api.repository.CompanyRepository;
import com.springboot.api.repository.EmployeeRepository;
import com.springboot.api.service.CompanyService;

import jakarta.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

    /*
     * Dependency injection
     */
    private CompanyRepository companyRepo;

    @Autowired // linking
    public CompanyServiceImpl(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepo.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepo.deleteById(id);
    }

    @Override
    public Company getCompanyById(Long id) {
        Company optComp = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Id does not exist in data base."));

        return optComp;
    }

    @Transactional
    @Override
    public Company updateCompanyById(Long id, Company company) {

        Company getCompToUpdate = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Id does not exist in data base"));

    
        if(company.getId() > 0) return null;

        // boolean verifyingEqualityNIF = !Objects.equals(company.getNIF(), getCompToUpdate.getNIF());
        if (company.getNIF().length() > 0) {
            getCompToUpdate.setNIF(company.getNIF());
        } else {
            throw new IllegalStateException("The NIF is incorrect, please try again");
        }

        boolean verifyingEqualityNomeCompany = !Objects.equals(company.getNomeCompany(),
                getCompToUpdate.getNomeCompany());
        if(company.getNomeCompany().length() > 0 && verifyingEqualityNomeCompany){
            getCompToUpdate.setNomeCompany(company.getNomeCompany());
        }else{
            throw new IllegalStateException("The name of company is invalid, please try again");
        }

        return getCompToUpdate;
    }
}
