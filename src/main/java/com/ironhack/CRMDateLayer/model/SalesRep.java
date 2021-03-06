package com.ironhack.CRMDateLayer.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "salesRep")
    private List<Lead> leadList;
    @OneToMany(mappedBy = "salesRep")
    private List<Opportunity> opportunityList;


    public SalesRep(){

    }

    public SalesRep(String name) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String shortPrint() {
        return this.id + " || " + this.name;
    }
}