package com.ironhack.CRMDateLayer.model;

public class SalesRep {
    private static Integer id;
    private String name;

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
}