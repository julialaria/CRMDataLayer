package com.ironhack.CRMDateLayer.model;

import javax.persistence.*;

@Entity
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private static int ID_COUNTER = 1;
    private String name;


    public SalesRep(){

    }

    public SalesRep(String name) {
        this.name = name;
        id++;
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
        return id + " || " + name;
    }
}