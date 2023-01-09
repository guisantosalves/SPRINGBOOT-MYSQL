package com.springboot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.api.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    
}
