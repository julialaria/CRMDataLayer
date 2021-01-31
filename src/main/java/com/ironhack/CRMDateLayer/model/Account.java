package com.ironhack.CRMDateLayer.model;

import com.ironhack.CRMDateLayer.enums.Industry;


import javax.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private static int ID_COUNTER = 1;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    @OneToMany
    @JoinColumn(name = "contact_id")
    private List<Contact> contactList;
    @OneToMany
    @JoinColumn(name = "opportunity_id")
    private List<Opportunity> opportunityList;

    public Account(){

    }

    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList, List<Opportunity> opportunityList) {
        this.id = id;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }

    public int getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }


    public int getEmployeeCount() {
        return employeeCount;
    }


    public String getCity() {
        return city;
    }


    public String getCountry() {
        return country;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    @Override
    public String toString() {
        return "Account with id "  + id +
                " belongs to " + industry +
                " industry, has " + employeeCount +
                " employees, is based on " + city + '\'' +
                ", " + country + '\'' +
                " with contact List " + contactList +
                " and opportunity List " + opportunityList ;
    }
}

