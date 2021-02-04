package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.enums.Industry;
import com.ironhack.CRMDateLayer.enums.Product;
import com.ironhack.CRMDateLayer.enums.Status;
import com.ironhack.CRMDateLayer.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class OpportunityRepositoryTest {

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Test
    void countOpportunitiesBySalesRep() {
        List<Object[]> response = opportunityRepository.countOpportunitiesBySalesRep();
        assertEquals(response.get(0)[0], "James");
        assertEquals(response.get(0)[1], new BigInteger("3"));
        assertEquals(response.get(1)[0], "Sara");
        assertEquals(response.get(1)[1], new BigInteger("1"));
        assertEquals(response.get(2)[0], "Julia");
        assertEquals(response.get(2)[1], new BigInteger("2"));
    }

    @Test
    void countOpportunitiesBySalesRepClosedWon() {
        List<Object[]> response = opportunityRepository.countOpportunitiesBySalesRepClosedWon();
        assertEquals(response.get(0)[0], "Julia");
        assertEquals(response.get(0)[1], new BigInteger("1"));
    }

    @Test
    void countOpportunitiesBySalesRepClosedLost() {
        List<Object[]> response = opportunityRepository.countOpportunitiesBySalesRepClosedLost();
        assertEquals(response.get(0)[0], "James");
        assertEquals(response.get(0)[1], new BigInteger("1"));
    }

    @Test
    void countOpportunitiesBySalesRepOpen() {
        List<Object[]> response = opportunityRepository.countOpportunitiesBySalesRepOpen();
        assertEquals(response.get(0)[0], "James");
        assertEquals(response.get(0)[1], new BigInteger("2"));
        assertEquals(response.get(1)[0], "Sara");
        assertEquals(response.get(1)[1], new BigInteger("1"));
        assertEquals(response.get(2)[0], "Julia");
        assertEquals(response.get(2)[1], new BigInteger("1"));
    }

    @Test
    void countOpportunitiesByProduct() {
        List<Object[]> response = opportunityRepository.countOpportunitiesByProduct();
        assertEquals(response.get(0)[0], "BOX");
        assertEquals(response.get(0)[1], new BigInteger("5"));
        assertEquals(response.get(1)[0], "FLATBED");
        assertEquals(response.get(1)[1], new BigInteger("1"));
    }

    @Test
    void countOpportunitiesByProductClosedWon() {
        List<Object[]> response = opportunityRepository.countOpportunitiesByProductClosedWon();
        assertEquals(response.get(0)[0], "BOX");
        assertEquals(response.get(0)[1], new BigInteger("1"));

    }

    @Test
    void countOpportunitiesByProductClosedLost() {
        List<Object[]> response = opportunityRepository.countOpportunitiesByProductClosedLost();
        assertEquals(response.get(0)[0], "BOX");
        assertEquals(response.get(0)[1], new BigInteger("1"));

    }

    @Test
    void countOpportunitiesByProductOpen() {
        List<Object[]> response = opportunityRepository.countOpportunitiesByProductOpen();
        assertEquals(response.get(0)[0], "BOX");
        assertEquals(response.get(0)[1], new BigInteger("3"));
        assertEquals(response.get(1)[0], "FLATBED");
        assertEquals(response.get(1)[1], new BigInteger("1"));
    }

    @Test
    void countOpportunitiesByCountry() {
        List<Object[]> response = opportunityRepository.countOpportunitiesByCountry();
        response.forEach(System.out::println);
        assertEquals(response.get(0)[0], "EEUU");
        assertEquals(response.get(0)[1], new BigInteger("2"));
        assertEquals(response.get(1)[0], "Spain");
        assertEquals(response.get(1)[1], new BigInteger("2"));
    }


    @Test
    void meanQuantityOfOrders() {
        List<Object[]> response = opportunityRepository.meanQuantityOfOrders();
        assertEquals(response.get(0)[0], new BigDecimal("600.0000"));
    }

    @Test
    void medianQuantityOfOrders() {
        List<Object[]> response = opportunityRepository.medianQuantityOfOrders();
        assertEquals(response.get(0)[0], 678);
    }

    @Test
    void maxQuantityOfOrders() {
        List<Object[]> response = opportunityRepository.maxQuantityOfOrders();
        assertEquals(response.get(0)[0], 986);
    }

    @Test
    void minQuantityOfOrders() {
        List<Object[]> response = opportunityRepository.minQuantityOfOrders();
        assertEquals(response.get(0)[0], 86);
    }


    @BeforeEach
    void setUp() {
        salesRepRepository.deleteAll();
        salesRepRepository.save(new SalesRep("James"));
        salesRepRepository.save(new SalesRep("Sara"));
        salesRepRepository.save(new SalesRep("Michael"));
        salesRepRepository.save(new SalesRep("Julia"));

        leadRepository.deleteAll();
        leadRepository.save(new Lead("Pepe Lopez", "677777777", "pepe@gmail.com",
                "Pepe company", salesRepRepository.findByName("James").get()));
        leadRepository.save(new Lead("Victor Cardozo", "688888888", "victor@gmail.com",
                "Ironhack", salesRepRepository.findByName("James").get()));
        leadRepository.save(new Lead("Elisa Martínez", "699999999", "elisa@gmail.com",
                "Elisa company", salesRepRepository.findByName("Sara").get()));
        leadRepository.save(new Lead("María García", "655555555", "maria@gmail.com",
                "Maria company", salesRepRepository.findByName("Julia").get()));

        accountRepository.deleteAll();
        accountRepository.save(new Account(Industry.ECOMMERCE, 40, "New York", "EEUU",
                new ArrayList<>(), new ArrayList<>()));
        accountRepository.save(new Account(Industry.MANUFACTURING, 840, "Madrid", "Spain",
                new ArrayList<>(), new ArrayList<>()));
        accountRepository.save(new Account(Industry.MEDICAL, 4, "Sevilla", "Spain",
                new ArrayList<>(), new ArrayList<>()));
        accountRepository.save(new Account(Industry.ECOMMERCE, 25, "Paris", "France",
                new ArrayList<>(), new ArrayList<>()));

        contactRepository.deleteAll();
        contactRepository.save(new Contact("Pepe Lopez", "677777777", "pepe@gmail.com",
                "Pepe company"));
        contactRepository.save(new Contact("Victor Cardozo", "688888888", "victor@gmail.com",
                "Ironhack"));
        contactRepository.save(new Contact("Elisa Martínez", "699999999", "elisa@gmail.com",
                "Elisa company"));
        contactRepository.save(new Contact("María García", "655555555", "maria@gmail.com",
                "Maria company"));

        opportunityRepository.deleteAll();
        opportunityRepository.save(new Opportunity(Product.BOX, 86, contactRepository.findByName("Pepe Lopez"),
                salesRepRepository.findByName("James").get()));
        opportunityRepository.save(new Opportunity(Product.FLATBED, 186, contactRepository.findByName("Victor Cardozo"),
                salesRepRepository.findByName("James").get()));
        opportunityRepository.save(new Opportunity(Product.BOX, 446, contactRepository.findByName("Elisa Martínez"),
                salesRepRepository.findByName("Sara").get()));
        opportunityRepository.save(new Opportunity(Product.BOX, 910, contactRepository.findByName("María García"),
                salesRepRepository.findByName("Julia").get()));
        opportunityRepository.save(new Opportunity(Product.BOX, 986, contactRepository.findByName("María García"), Status.CLOSED_LOST, salesRepRepository.findByName("James").get()));
        opportunityRepository.save(new Opportunity(Product.BOX, 986, contactRepository.findByName("María García"), Status.CLOSED_WON, salesRepRepository.findByName("Julia").get()));


    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }
}