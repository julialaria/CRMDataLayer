package com.ironhack.CRMDateLayer.model;

import com.ironhack.CRMDateLayer.enums.Status;
import com.ironhack.CRMDateLayer.enums.Product;
import com.ironhack.CRMDateLayer.enums.Status;
import com.ironhack.CRMDateLayer.repository.*;
import com.ironhack.CRMDateLayer.style.ConsoleColors;


import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class MainMethods {

    public static Lead newLead(SalesRepRepository salesRepRepository){

        Scanner scan = new Scanner(System.in);
        String nameLead="";
        String phoneNumber;
        String email;
        int idNum;
        String idSalesRep;
        SalesRep salesRep = null;
        boolean isValid = false;

        while(!isValid){
            System.out.println(ConsoleColors.BLUE+"Please specify the name of the lead");
            nameLead = scan.nextLine();
            isValid = nameLead.matches("[a-zA-Z]+");
            if(!isValid){
                System.out.println(ConsoleColors.RED+"The name of the lead is not valid. Can contain only letters");
            }
        }

        while(true){
            System.out.println(ConsoleColors.BLUE+ "Introduce phone number of the Lead");
            phoneNumber = scan.nextLine();
            try{
                Lead.isPhoneNumberValid(phoneNumber);
                break;
            } catch(IllegalArgumentException e){
                System.err.println(ConsoleColors.RED +"Phone number not valid, introduce it with the following format: " +
                        "612345678 / 612-345-678 / 612 345 678 / 612 34 56 78");
            }
        }

        while(true){
            System.out.println(ConsoleColors.BLUE+"Introduce email of the Lead");
            email = scan.nextLine();
            try{
                Lead.isEmailValid(email);
                break;
            }catch (IllegalArgumentException e){
                System.err.println(ConsoleColors.RED +"Introduce a valid email address");
            }

        }

        while(true){
            System.out.println(ConsoleColors.BLUE+"Introduce id of the SalesRep");
            idSalesRep = scan.nextLine();
            try{
                idNum = Integer.parseInt(idSalesRep);

                if(salesRepRepository.findById(idNum).isPresent()){
                    break;
                }
                else{
                    System.err.println(ConsoleColors.RED +"Introduce a valid id SalesRep");
                }
            }catch (IllegalArgumentException e){
                System.err.println(ConsoleColors.RED +"Introduce a valid id SalesRep");
            }

        }

        System.out.println(ConsoleColors.BLUE+"Introduce company name of the Lead");
        String companyName = scan.nextLine();
        Lead lead = new Lead(nameLead, phoneNumber, email, companyName, salesRepRepository.findById(idNum).get());
        return lead;
    }

    public static Contact convertLeadToContact(LeadRepository leadRepository, int idLead){

        String name = leadRepository.findById(idLead).get().getName();
        String phoneNumber = leadRepository.findById(idLead).get().getPhoneNumber();
        String email = leadRepository.findById(idLead).get().getEmail();
        String companyName = leadRepository.findById(idLead).get().getCompanyName();

        Contact contact = new Contact(name, phoneNumber, email, companyName);
        return contact;
    }

    public static void lookupOpportunity(String[] orderSplit, OpportunityRepository opportunityRepository){
        int idOportunity=Integer.parseInt(orderSplit[2]);

        if (opportunityRepository.findById(idOportunity).isPresent()){
            System.out.println(opportunityRepository.findById(idOportunity).get().toString());}
        else {
            System.out.println(ConsoleColors.RED +"Opportunity Id is not valid");
        }
    }

    public static void lookupLead(String[] orderSplit, LeadRepository leadRepository){
        int idLead = Integer.parseInt(orderSplit[2]);
        if (leadRepository.findById(idLead).isPresent()){
            System.out.println(leadRepository.findById(idLead).get().toString());}
        else {
            System.out.println(ConsoleColors.RED +"Lead Id is not valid");
        }
    }

    public static void showLeads(LeadRepository leadRepository){
        System.out.println("LEAD ID || LEAD NAME");
        for (Lead lead : leadRepository.findAll()){
            System.out.println(lead.shortPrint());
        }
    }

    public static void closeLost(String[] orderSplit, OpportunityRepository opportunityRepository){
        int idOpportunity = Integer.parseInt(orderSplit[1]);
        if (opportunityRepository.findById(idOpportunity).isPresent()){
            System.out.println(ConsoleColors.BLUE +"Opportunity with id "+idOpportunity+" changed to close-lost");
            opportunityRepository.findById(idOpportunity).get().setStatus(Status.CLOSED_LOST);}
        else {
            System.out.println(ConsoleColors.RED +"Opportunity id is not valid");
        }
    }

    public static void closeWon(String[] orderSplit, OpportunityRepository opportunityRepository){
        int idOpportunity = Integer.parseInt(orderSplit[1]);
        if (opportunityRepository.findById(idOpportunity).isPresent()){
            System.out.println(ConsoleColors.BLUE +"Opportunity with id "+idOpportunity+" changed to close-won");
            opportunityRepository.findById(idOpportunity).get().setStatus(Status.CLOSED_WON);}
        else {
            System.out.println(ConsoleColors.RED +"Opportunity id is not valid");
        }
    }

    public static void newSalesRep(SalesRepRepository salesRepRepository){

        Scanner scan = new Scanner(System.in);
        String name="";
        boolean isValid = false;

        while(!isValid){
            System.out.println(ConsoleColors.BLUE+"Please specify the name of the lead");
            name = scan.nextLine();
            isValid = name.matches("[a-zA-Z]+");
            if(!isValid){
                System.out.println(ConsoleColors.RED+"The name of the lead is not valid. Can contain only letters");
            }
        }

        SalesRep salesRep = new SalesRep(name);
        salesRepRepository.save(salesRep);

    }

    public static void showSalesReps(SalesRepRepository salesRepRepository){
        System.out.println("LEAD ID || LEAD NAME");
        for (SalesRep salesRep : salesRepRepository.findAll()){
            System.out.println(salesRep.shortPrint());
        }
    }

    public static String whatNext(){
        String[] keyPhrases = new String[]{"NEW LEAD", "CONVERT + ID", "LOOKUP LEAD + ID", "LOOKUP OPPORTUNITY + ID", "SHOW LEADS", "CLOSE-LOST + ID", "CLOSE-WON + ID"};
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE +"What do you want to do next? " + Arrays.toString(keyPhrases));
        System.out.println(ConsoleColors.GREEN + "(Write 'EXIT' to close terminal)");
        String order = scan.nextLine();
        String[] orderSplit = order.split(" ");
        return order;
    }
}
