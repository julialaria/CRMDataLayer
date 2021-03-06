package com.ironhack.CRMDateLayer.model;

import static org.junit.jupiter.api.Assertions.*;

import com.ironhack.CRMDateLayer.enums.Industry;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class AccountTest {
    @Test
    void generate_correct_id() {
        Account account = new Account(Industry.ECOMMERCE,2,"Madrid","España", null, null);
        Account account2 = new Account(Industry.ECOMMERCE,2,"Madrid","España", null, null);
        assertEquals(2, account2.getId());
    }
}