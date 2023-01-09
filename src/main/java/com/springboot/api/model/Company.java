package com.springboot.api.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="companies")
public class Company {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)// strategy of pk
    private long id;

    @Column(name = "name_company")
    private String nomeCompany;
    
    @Column(name = "NIF")
    private String NIF;

    // mappedBy -> campo na outra entity que vai representar o relacionamento
    @OneToMany(targetEntity = Employee.class, mappedBy = "company", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Employee> employees;

    public Company(){

    }

    public Company(Long id, String nomeCompany, String NIF, Employee employee){
        this.id = id;
        this.nomeCompany = nomeCompany;
        this.NIF = NIF;
    }

    public Company(String nomeCompany, String NIF, Employee employee){
        this.nomeCompany = nomeCompany;
        this.NIF = NIF;
    }

    /*
     * id
     */
    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }

    
    /*
     * nomeCompany
     */
    public String getNomeCompany(){
        return this.nomeCompany;
    }
    public void setNomeCompany(String nomeCompany){
        this.nomeCompany = nomeCompany;
    }

    
    /*
     * NIF
     */
    public String getNIF(){
        return this.NIF;
    }
    public void setNIF(String NIF){
        this.NIF = NIF;
    }

    /*
     * employee mapping
     */
    public Set<Employee> employees(){
        return this.employees;
    }
    public void employees(Set<Employee> employee){
        this.employees = employee;
    }
}
