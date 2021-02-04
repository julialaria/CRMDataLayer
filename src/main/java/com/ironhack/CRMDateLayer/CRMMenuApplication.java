package com.ironhack.CRMDateLayer;

import com.ironhack.CRMDateLayer.enums.*;
import com.ironhack.CRMDateLayer.model.*;
import com.ironhack.CRMDateLayer.repository.*;
import com.ironhack.CRMDateLayer.style.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
@Profile("!test")
public class CRMMenuApplication implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        addSampleData();

        // MAINMETHODS
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE_BACKGROUND + "\n WELCOME TO CRM-SYSTEM \n");
        System.out.println(ConsoleColors.BLUE + "\nPlease write a order: \n(Write 'COMMANDS HELP' if you need to show available commands)");
        String order = scan.nextLine();
        String[] orderSplit = order.split(" ");

        String[] keyPhrases = new String[]{"NEW LEAD", "CONVERT", "LOOKUP OPPORTUNITY", "LOOKUP LEAD", "SHOW LEADS",
                "CLOSE-LOST", "CLOSE-WON", "NEW SALESREP", "SHOW SALESREPS"};

        String[] bySalesRep = new String[]{"Report Lead by SalesRep", "Report Opportunity by SalesRep", "Report CLOSED-WON by SalesRep",
                "Report CLOSED-LOST by SalesRep", "Report OPEN by SalesRep"};
        String[] byProduct = new String[]{"Report Opportunity by product", "Report CLOSED-WON by product",
                "Report CLOSED-LOST by product", "Report OPEN by product"};
        String[] byCountry = new String[]{"Report Opportunity by Country", "Report CLOSED-WON by Country",
                "Report CLOSED-LOST by Country", "Report OPEN by Country"};
        String[] byCity = new String[]{"Report Opportunity by City", "Report CLOSED-WON by City",
                "Report CLOSED-LOST by City", "Report OPEN by City"};
        String[] byIndustry = new String[]{"Report Opportunity by Industry", "Report CLOSED-WON by Industry",
                "Report CLOSED-LOST by Industry", "Report OPEN by Industry"};
        String[] employeeCountStates = new String[]{"Mean EmployeeCount", "Median EmployeeCount", "Max EmployeeCount",
                "Min EmployeeCount"};
        String[] quantityStates = new String[]{"Mean Quantity", "Median Quantity", "Max Quantity", "Min Quantity"};
        String[] opportunityStates = new String[]{"Mean Opps per Account", "Median Opps per Account", "Max Opps per Account",
                "Min Opps per Account"};

        while (!order.equalsIgnoreCase("EXIT")) {
            if (orderSplit.length > 1) {
                if ((order.toUpperCase().equals(keyPhrases[0]))) {
                    Lead lead = MainMethods.newLead(salesRepRepository);
                    leadRepository.save(lead);
                } else if (orderSplit[0].toUpperCase().equals(keyPhrases[1])) {
                    try {
                        int idLead = Integer.parseInt(orderSplit[1]);
                        if (leadRepository.findById(idLead).isPresent() && orderSplit.length == 2) {
                            SalesRep salesRep = leadRepository.findById(idLead).get().getSalesRep();

                            Contact contact = MainMethods.convertLeadToContact(leadRepository, idLead);
                            contactRepository.save(contact);

                            Opportunity opportunity = CreateOpportunity.create(contact, salesRep);
                            opportunityRepository.save(opportunity);

                            Account account = CreateAccount.create(contact, opportunity, accountRepository);
                            accountRepository.save(account);

                            leadRepository.deleteById(idLead);

                        } else {
                            System.out.println(ConsoleColors.RED + "This Lead id is not valid, please check Leads with command 'SHOW LEADS'");
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println(ConsoleColors.RED + "Introduce a valid Lead id");
                    }
                } else if ((orderSplit[0] + " " + orderSplit[1]).toUpperCase().equals(keyPhrases[2]) && orderSplit.length > 2) {
                    MainMethods.lookupOpportunity(orderSplit, opportunityRepository);
                } else if ((orderSplit[0] + " " + orderSplit[1]).toUpperCase().equals(keyPhrases[3]) && orderSplit.length > 2) {
                    MainMethods.lookupLead(orderSplit, leadRepository);
                } else if (order.toUpperCase().equals(keyPhrases[4])) {
                    MainMethods.showLeads(leadRepository);
                } else if (orderSplit[0].toUpperCase().equals(keyPhrases[5])) {
                    MainMethods.closeLost(orderSplit, opportunityRepository);
                } else if (orderSplit[0].toUpperCase().equals(keyPhrases[6])) {
                    MainMethods.closeWon(orderSplit, opportunityRepository);
                } else if (order.toUpperCase().equals(keyPhrases[7])) {

                    MainMethods.newSalesRep(salesRepRepository);

                } else if (order.toUpperCase().equals(keyPhrases[8])) {
                    MainMethods.showSalesReps(salesRepRepository);


                } else if(order.toUpperCase().equals(bySalesRep[0].toUpperCase())){
                    printResult(leadRepository.getLeadsBySalesRep());
                }else if(order.toUpperCase().equals(bySalesRep[1].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesBySalesRep());
                }else if(order.toUpperCase().equals(bySalesRep[2].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesBySalesRepClosedWon());
                }else if(order.toUpperCase().equals(bySalesRep[3].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesBySalesRepClosedLost());
                }else if(order.toUpperCase().equals(bySalesRep[4].toUpperCase())){

                    printResult(opportunityRepository.countOpportunitiesBySalesRepOpen());
                }else if(order.toUpperCase().equals(byProduct[0].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByProduct());
                }else if(order.toUpperCase().equals(byProduct[1].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByProductClosedWon());
                }else if(order.toUpperCase().equals(byProduct[2].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByProductClosedLost());
                }else if(order.toUpperCase().equals(byProduct[3].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByProductOpen());

                }else if(order.toUpperCase().equals(byCountry[0].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCountry());
                }else if(order.toUpperCase().equals(byCountry[1].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCountryClosedWon());
                }else if(order.toUpperCase().equals(byCountry[2].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCountryClosedLost());
                }else if(order.toUpperCase().equals(byCountry[3].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCountryOpen());

                }else if(order.toUpperCase().equals(byCity[0].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCity());
                }else if(order.toUpperCase().equals(byCity[1].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCityClosedWon());
                }else if(order.toUpperCase().equals(byCity[2].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCityClosedLost());
                }else if(order.toUpperCase().equals(byCity[3].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByCityOpen());


                }else if(order.toUpperCase().equals(byIndustry[0].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByIndustry());
                }else if(order.toUpperCase().equals(byIndustry[1].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByIndustryClosedWon());
                }else if(order.toUpperCase().equals(byIndustry[2].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByIndustryClosedLost());
                }else if(order.toUpperCase().equals(byIndustry[3].toUpperCase())){
                    printResult(opportunityRepository.countOpportunitiesByIndustryOpen());

                }else if(order.toUpperCase().equals(employeeCountStates[0].toUpperCase())){
                    printResult(accountRepository.meanEmployeeCount());
                }else if(order.toUpperCase().equals(employeeCountStates[1].toUpperCase())){
                    accountRepository.setRowIndex();
                    printResult(accountRepository.medianEmployeeCount());
                }else if(order.toUpperCase().equals(employeeCountStates[2].toUpperCase())){
                    printResult(accountRepository.maxEmployeeCount());
                }else if(order.toUpperCase().equals(employeeCountStates[3].toUpperCase())){
                    printResult(accountRepository.minEmployeeCount());

                }else if(order.toUpperCase().equals(quantityStates[0].toUpperCase())){
                    printResult(opportunityRepository.meanQuantityOfOrders());
                }else if(order.toUpperCase().equals(quantityStates[1].toUpperCase())){
                    opportunityRepository.setRowIndex();
                    printResult(opportunityRepository.medianQuantityOfOrders());
                }else if(order.toUpperCase().equals(quantityStates[2].toUpperCase())){
                    printResult(opportunityRepository.maxQuantityOfOrders());
                }else if(order.toUpperCase().equals(quantityStates[3].toUpperCase())){
                    printResult(opportunityRepository.minQuantityOfOrders());

                }else if(order.toUpperCase().equals(opportunityStates[0].toUpperCase())){
                    printResult(opportunityRepository.averageOpportunitiesInAccount());
                }else if(order.toUpperCase().equals(opportunityStates[1].toUpperCase())){
                    opportunityRepository.setRowIndex();
                    printResult(opportunityRepository.medianQuantityOfOrders());
                }else if(order.toUpperCase().equals(opportunityStates[2].toUpperCase())){
                    printResult(opportunityRepository.maxOpportunitiesInAccount());
                }else if(order.toUpperCase().equals(opportunityStates[3].toUpperCase())){
                    printResult(opportunityRepository.minOpportunitiesInAccount());


                } else if(order.toUpperCase().equals("COMMANDS HELP")){
                    System.out.println("===================GENERAL COMMANDS===================\n" +
                            "NEW LEAD -- create a new lead\n" +
                            "CONVERT {idLead} -- convert a lead to a opportunity\n" +
                            "LOOKUP OPPORTUNITY {idOpportunity} -- show info of an opportunity\n" +
                            "LOOKUP LEAD {idLead} -- show info of a lead\n" +
                            "SHOW LEADS -- show info of leads\n" +
                            "CLOSE-LOST {idOpportunity} -- set opportunity status to close-lost\n" +
                            "CLOSE-WON {idOpportunity} -- set opportunity status to close-won\n" +
                            "NEW SALESREP -- create a new salesRep\n" +
                            "SHOW SALESREPS -- show info of salesReps\n" +
                            "===================REPORTING COMMANDS===================\n" +
                            "===================BY SALESREP===================\n" +
                            "REPORT LEAD BY SALESREP -- count of Leads by SalesRep\n" +
                            "REPORT OPPORTUNITY BY SALESREP -- count of all Opportunities by SalesRep\n" +
                            "REPORT CLOSED-WON BY SALESREP -- count of all CLOSED_WON Opportunities by SalesRep\n" +
                            "REPORT CLOSED-LOST BY SALESREP -- count of all CLOSED_LOST Opportunities by SalesRep\n" +
                            "REPORT OPEN BY SALESREP -- count of all OPEN Opportunities by SalesRep\n" +
                            "===================BY PRODUCT===================\n" +
                            "REPORT OPPORTUNITY BY PRODUCT -- count of all Opportunities by product\n" +
                            "REPORT CLOSED-WON BY PRODUCT -- count of all CLOSED_WON Opportunities by product\n" +
                            "REPORT CLOSED-LOST BY PRODUCT -- count of all CLOSED_LOST Opportunities by product\n" +
                            "REPORT OPEN BY PRODUCT -- count of all OPEN Opportunities by product\n" +
                            "===================BY COUNTRY===================\n" +
                            "REPORT OPPORTUNITY BY COUNTRY -- count of all Opportunities by country\n" +
                            "REPORT CLOSED-WON BY COUNTRY -- count of all CLOSED_WON Opportunities by country\n" +
                            "REPORT CLOSED-LOST BY COUNTRY -- count of all CLOSED_LOST Opportunities by country\n" +
                            "REPORT OPEN BY COUNTRY -- count of all OPEN Opportunities by country\n" +
                            "===================BY CITY===================\n" +
                            "REPORT OPPORTUNITY BY CITY -- count of all Opportunities by city\n" +
                            "REPORT CLOSED-WON BY CITY -- count of all CLOSED_WON Opportunities by city\n" +
                            "REPORT CLOSED-LOST BY CITY -- count of all CLOSED_LOST Opportunities by city\n" +
                            "REPORT OPEN BY CITY -- count of all OPEN Opportunities by city\n" +
                            "===================BY INDUSTRY===================\n" +
                            "REPORT OPPORTUNITY BY INDUSTRY -- count of all Opportunities by industry\n" +
                            "REPORT CLOSED-WON BY INDUSTRY -- count of all CLOSED_WON Opportunities by industry\n" +
                            "REPORT CLOSED-LOST BY INDUSTRY -- count of all CLOSED_LOST Opportunities by industry\n" +
                            "REPORT OPEN BY INDUSTRY -- count of all OPEN Opportunities by industry\n" +
                            "===================EMPLOYEECOUNT STATES===================\n" +
                            "MEAN EMPLOYEECOUNT -- mean employeeCount\n" +
                            "MEDIAN EMPLOYEECOUNT -- median employeeCount\n" +
                            "MAX EMPLOYEECOUNT -- maximum employeeCount\n" +
                            "MIN EMPLOYEECOUNT -- minimum employeeCount\n" +
                            "===================QUANTITY STATES===================\n" +
                            "MEAN QUANTITY -- mean quanitity of products order\n" +
                            "MEDIAN QUANTITY -- median quanitity of products order\n" +
                            "MAX QUANTITY -- maximum quanitity of products order\n" +
                            "MIN QUANTITY -- minimum quanitity of products order\n" +
                            "===================OPPORTUNITY STATES===================\n" +
                            "MEAN OPPS PER ACCOUNT -- mean number of Opportunities associated with an Account\n" +
                            "MEDIAN OPPS PER ACCOUNT -- median number of Opportunities associated with an Account\n" +
                            "MAX OPPS PER ACCOUNT -- maximum number of Opportunities associated with an Account\n" +
                            "MIN OPPS PER ACCOUNT -- minimum number of Opportunities associated with an Account\n"
                    );

                } else {
                    System.out.println(ConsoleColors.RED + "COMMAND NOT FOUND");
                }
            } else {
                System.out.println(ConsoleColors.RED + "COMMAND NOT FOUND");
            }
            order = MainMethods.whatNext();
            orderSplit = order.split(" ");
        }
    }

    private void addSampleData() {
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
        opportunityRepository.save(new Opportunity(Product.BOX, 986, contactRepository.findByName("María García"),
                salesRepRepository.findByName("Julia").get()));
    }
    private void printResult (List<Object[]> objects){

        if (objects.size()==0){
            System.out.println("There is no data for your request");
        }

        for (int i=0;i<objects.size();i++){
            for (int j=0;j<objects.get(i).length;j++){
                System.out.print(objects.get(i)[j]);
                if (j!=objects.get(i).length-1){
                    System.out.print("\t");
                }
            }
            if (i!= objects.size()){
                System.out.print("\n");
            }
        }

    }
}
