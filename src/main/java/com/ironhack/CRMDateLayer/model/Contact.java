package com.ironhack.CRMDateLayer.model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Contact extends Lead {

    private int idContact;
    private static int idCounter = 1;
    @OneToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    SalesRep salesRep;


    public Contact() {

    }

    public Contact(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) {
        super(name, phoneNumber, email, companyName, salesRep);
        this.idContact = idContact;
        this.lead = lead;
        idContact++;
        this.salesRep = salesRep;
    }

    public int getIdContact() {
        return idContact;
    }

    public Lead getLead() {
        return lead;
    }

    @Override
    public SalesRep getSalesRep() {
        return salesRep;
    }

    @Override
    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    @Override
    public String toString() {
        return "Contact with id" + idContact +
                ", corresponds to lead: " + lead;
    }

}
