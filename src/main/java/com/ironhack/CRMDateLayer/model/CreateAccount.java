package com.ironhack.CRMDateLayer.model;


import com.ironhack.CRMDateLayer.enums.Industry;
import com.ironhack.CRMDateLayer.repository.*;
import com.ironhack.CRMDateLayer.style.ConsoleColors;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import static java.util.List.of;

public class CreateAccount {

    private static final Scanner sc = new Scanner(System.in);

    public static Account create(Contact contact, Opportunity opportunity, AccountRepository accountRepository) {
        Industry industry;
        int employees;
        String city;
        String country;
        Account account;

        System.out.println(ConsoleColors.BLUE + "Would you like to create a new Account?(Y/N)");
        Scanner scan = new Scanner(System.in);
        String newAccount = scan.nextLine();
        while(true){
            if ((newAccount).equalsIgnoreCase("Y")){
                System.out.println(ConsoleColors.BLUE + "We are going to create an Account");
                industry = enterCorrectIndustry(sc);
                employees = getEmployees(sc);
                city = getCity(sc);
                country = getCountry(sc);
                account = new Account(industry, employees, city, country, of(contact), of(opportunity));
                break;
            }
            else if ((newAccount).equalsIgnoreCase("N")){
                System.out.println(ConsoleColors.BLUE + "Please introduce id of your existing Account");
                String idAccount = scan.nextLine();
                try{
                    int idAccountNum = Integer.parseInt(idAccount);
                    if(accountRepository.findById(idAccountNum).isPresent()){
                        account = accountRepository.findById(idAccountNum).get();
                        break;
                    }
                }catch (IllegalArgumentException e){
                    System.err.println(ConsoleColors.RED +"Introduce a valid id SalesRep");
                }
            }
            else {
                System.err.println(ConsoleColors.RED +"Introduce a valid response. Would you like to create a new Account?(Y/N)");
            }
        }
        return account;
    }

    public static String getCountry(Scanner scanner) {
        String country = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.println(ConsoleColors.BLUE + "Please specify your country");
            country = scanner.nextLine();
            isValid = country.matches("[a-zA-Zñ]+");
            if (!isValid) {
                System.out.println(ConsoleColors.RED + "The country must be written and can not have numbers");
            }
        }

        return country;
    }

    public static String getCity(Scanner scanner) {
        String city = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.println(ConsoleColors.BLUE + "Please specify your city");
            city = scanner.nextLine();
            isValid = city.matches("[a-zA-Zñ]+");
            if (!isValid) {
                System.out.println(ConsoleColors.RED + "The city must be written and can not have numbers");
            }
        }
        return city;

    }

    public static int getEmployees(Scanner scanner) {
        System.out.println(ConsoleColors.BLUE + "Please specify your employee count");
        int employees = 0;
        boolean isValidemployees = false;
        while (!isValidemployees) {
            try {
                employees = Integer.parseInt(scanner.nextLine());
                isValidemployees = isValidEmployees(employees);
            } catch (Exception e) {
            }
            if (!isValidemployees) {
                System.out.println(ConsoleColors.RED + "Specify a correct number > 0");
            }
        }
        return employees;
    }

    public static Industry enterCorrectIndustry(Scanner scanner) {
        System.out.println(ConsoleColors.BLUE + "Please specify your industry: (Options: 'PRODUCE', 'ECOMMERCE', 'MANUFACTURING','MEDICAL','OTHER')");
        String typeAccount = scanner.nextLine().toUpperCase(Locale.ROOT);
        while (!isCorrectIndustry(typeAccount)) {
            typeAccount = scanner.nextLine().toUpperCase(Locale.ROOT);
        }
        return Industry.valueOf(typeAccount);

    }

    public static boolean isCorrectIndustry(String test) {
        for (Industry industry : Industry.values()) {
            if (industry.name().equals(test)) {
                return true;
            }
        }
        System.out.println(ConsoleColors.RED + "Incorrect value, Correct values are: 'PRODUCE', 'ECOMMERCE', 'MANUFACTURING','MEDICAL','OTHER'");
        return false;
    }

    public static boolean isValidEmployees(int value) {
        return value > 0;
    }
}

