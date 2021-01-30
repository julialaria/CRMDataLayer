package com.ironhack.CRMDateLayer.model;
import com.ironhack.CRMDateLayer.enums.Product;
import com.ironhack.CRMDateLayer.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static int id;
    Product product;
    int quantity;
    Contact decisionMaker;
    Status status;
    private SalesRep salesrep;

    public Opportunity(Product product, int quantity, Contact decisionMaker, SalesRep salesrep) {

        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = Status.OPEN;
        this.salesrep = salesrep;
        id++;
    }

    @Override
    public String toString() {

        return "Opportunity with product " + product +
                ", quantity = " + quantity +
                ", decision maker contact id is " + decisionMaker.getIdContact() +
                " and its status is " + status ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opportunity)) return false;
        Opportunity that = (Opportunity) o;
        return getQuantity() == that.getQuantity() && getProduct() == that.getProduct() && getDecisionMaker().equals(that.getDecisionMaker()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQuantity(), getDecisionMaker(), getStatus());
    }

    public SalesRep getSalesrep() {
        return salesrep;
    }

    public void setSalesrep(SalesRep salesrep) {
        this.salesrep = salesrep;
    }

    public static int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
