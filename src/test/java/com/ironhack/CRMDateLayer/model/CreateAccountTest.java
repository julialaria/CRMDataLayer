package com.ironhack.CRMDateLayer.model;

import static com.ironhack.CRMDateLayer.model.CreateAccount.*;
import static org.junit.jupiter.api.Assertions.*;

import  com.ironhack.CRMDateLayer.enums.Industry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import com.ironhack.CRMDateLayer.model.CreateAccount.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class CreateAccountTest {

    @Test
    void create_correct_produce_industry_on_account() {
        Industry industry = enterCorrectIndustry(new Scanner("PRODUCE"));

        assertEquals(Industry.PRODUCE, industry);
    }

    @Test
    void create_correct_ecommerce_industry_on_account() {
        Industry industry = enterCorrectIndustry(new Scanner("ECOMMERCE"));

        assertEquals(Industry.ECOMMERCE, industry);
    }

    @Test
    void create_correct_manufracturing_industry_on_account() {
        Industry industry = enterCorrectIndustry(new Scanner("MANUFACTURING"));

        assertEquals(Industry.MANUFACTURING, industry);
    }

    @Test
    void create_correct_medical_industry_on_account() {
        Industry industry = enterCorrectIndustry(new Scanner("MEDICAL"));

        assertEquals(Industry.MEDICAL, industry);
    }

    @Test
    void create_correct_employees_on_account() {
        int employees = getEmployees(new Scanner("10"));

        assertEquals(10, employees);
    }

    @Test
    void create_correct_city_on_account() {
        String city = getCity(new Scanner("Madrid"));

        assertEquals("Madrid", city);
    }

    @Test
    void create_correct_country_on_account() {
        String country = getCountry(new Scanner("Francia"));

        assertEquals("Francia", country);
    }

    @Test
    void incorrect_get_employees() {
        assertFalse(isValidEmployees(-5));
        assertTrue(isValidEmployees(15));
    }
}