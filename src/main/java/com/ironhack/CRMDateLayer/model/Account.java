package com.ironhack.CRMDateLayer.model;

import com.ironhack.CRMDateLayer.enums.Industry;
import org.springframework.cache.annotation.EnableCaching;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static int ID_COUNTER = 1;
    private final int id;
    private final Industry industry;
    private final int employeeCount;
    private final String city;
    private final String country;
    private final List<Contact> contactList;
    private final List<Opportunity> opportunityList;

    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList, List<Opportunity> opportunityList) {
        this.id = ID_COUNTER++;
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

