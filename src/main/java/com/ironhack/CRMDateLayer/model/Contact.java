package com.ironhack.CRMDateLayer.model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Contact extends Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContact;
    private static int idCounter = 1;
    private Lead lead;

    public Contact(String name, String phoneNumber, String email, String companyName, SalesRep salesrep) {
        super(name, phoneNumber, email, companyName, salesrep);
        this.idContact = idContact;
        this.lead = lead;
        idContact++;
    }

    public int getIdContact() {
        return idContact;
    }

    public Lead getLead() {
        return lead;
    }

    @Override
    public String toString() {
        return "Contact with id" + idContact +
                ", corresponds to lead: " + lead ;
    }

}
