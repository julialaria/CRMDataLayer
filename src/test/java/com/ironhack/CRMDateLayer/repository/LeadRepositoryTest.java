package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.model.Lead;
import com.ironhack.CRMDateLayer.model.SalesRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class LeadRepositoryTest {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Test
    void getLeadsBySalesRep() {
        List<Object[]> leadsBySalesRep =  leadRepository.getLeadsBySalesRep();
        assertEquals(leadsBySalesRep.get(0)[0],"James");
        assertEquals(leadsBySalesRep.get(0)[1],new BigInteger("2"));
        assertEquals(leadsBySalesRep.get(1)[0],"Sara");
        assertEquals(leadsBySalesRep.get(1)[1],new BigInteger("1"));
        assertEquals(leadsBySalesRep.get(2)[0],"Julia");
        assertEquals(leadsBySalesRep.get(2)[1],new BigInteger("1"));
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
    }
}