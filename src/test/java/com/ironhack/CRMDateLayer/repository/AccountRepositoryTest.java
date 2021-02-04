package com.ironhack.CRMDateLayer.repository;

import com.ironhack.CRMDateLayer.enums.Industry;
import com.ironhack.CRMDateLayer.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

        accountRepository.save(new Account(Industry.ECOMMERCE, 40, "New York", "EEUU",
                new ArrayList<>(), new ArrayList<>()));
        accountRepository.save(new Account(Industry.MANUFACTURING, 840, "Madrid", "Spain",
                new ArrayList<>(), new ArrayList<>()));
        accountRepository.save(new Account(Industry.MEDICAL, 4, "Sevilla", "Spain",
                new ArrayList<>(), new ArrayList<>()));
        accountRepository.save(new Account(Industry.ECOMMERCE, 28, "Paris", "France",
                new ArrayList<>(), new ArrayList<>()));

    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void meanEmployeeCount() {
        List<Object[]> response = accountRepository.meanEmployeeCount();
        assertEquals(response.get(0)[0], new BigDecimal("228.0000"));

    }

    @Test
    void medianEmployeeCount() {
        accountRepository.setRowIndex();
        List<Object[]> response = accountRepository.medianEmployeeCount();
        assertEquals(response.get(0)[0], new BigDecimal("34.0000"));
    }

    @Test
    void maxEmployeeCount() {
        List<Object[]> response = accountRepository.maxEmployeeCount();
        assertEquals(response.get(0)[0], 840);
    }

    @Test
    void minEmployeeCount() {
        List<Object[]> response = accountRepository.minEmployeeCount();
        assertEquals(response.get(0)[0], 4);
    }


}