package com.springboot.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// create automatically when start the server

@Entity
@Table(name = "employees")  
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//strategy of PK
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // campo mapeado por company
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company; 

    public Employee(){

    }

    public Employee(long id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
     * lastName
     */
    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /*
     * firstname
     */

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /*
     * Email
     */
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    /*
     * fk
     */
    public Company getCompany(){
        return this.company;
    }
    public void setCompany(Company company){
        this.company = company;
    }
}

