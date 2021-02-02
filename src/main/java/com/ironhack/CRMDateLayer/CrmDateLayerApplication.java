package com.ironhack.CRMDateLayer;

import com.ironhack.CRMDateLayer.enums.*;
import com.ironhack.CRMDateLayer.model.*;
import com.ironhack.CRMDateLayer.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.*;

import java.util.*;

@SpringBootApplication
public class CrmDateLayerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrmDateLayerApplication.class, args);

	}

	@Component
	public class MyRunner implements CommandLineRunner{

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

			salesRepRepository.deleteAll();
			salesRepRepository.save(new SalesRep("James"));
			salesRepRepository.save(new SalesRep("Sara"));
			salesRepRepository.save(new SalesRep("Michael"));
			salesRepRepository.save(new SalesRep("Julia"));

			leadRepository.deleteAll();
			leadRepository.save(new Lead("Pepe Lopez", "677777777", "pepe@gmail.com",
					"Pepe company", salesRepRepository.findByName("James")));
			leadRepository.save(new Lead("Victor Cardozo", "688888888", "victor@gmail.com",
					"Ironhack", salesRepRepository.findByName("James")));
			leadRepository.save(new Lead("Elisa Martínez", "699999999", "elisa@gmail.com",
					"Elisa company", salesRepRepository.findByName("Sara")));
			leadRepository.save(new Lead("María García", "655555555", "maria@gmail.com",
					"Maria company", salesRepRepository.findByName("Julia")));

			accountRepository.deleteAll();
			accountRepository.save(new Account(Industry.ECOMMERCE, 40, "New York", "EEUU", new ArrayList<>(), new ArrayList<>()));
			accountRepository.save(new Account(Industry.MANUFACTURING, 840, "Madrid", "Spain", new ArrayList<>(), new ArrayList<>()));
			accountRepository.save(new Account(Industry.MEDICAL, 4, "Sevilla", "Spain", new ArrayList<>(), new ArrayList<>()));
			accountRepository.save(new Account(Industry.ECOMMERCE, 25, "Paris", "France", new ArrayList<>(), new ArrayList<>()));

			contactRepository.deleteAll();
			contactRepository.save(new Contact("Pepe Lopez", "677777777", "pepe@gmail.com", "Pepe company"));
			contactRepository.save(new Contact("Victor Cardozo", "688888888", "victor@gmail.com", "Ironhack"));
			contactRepository.save(new Contact("Elisa Martínez", "699999999", "elisa@gmail.com", "Elisa company"));
			contactRepository.save(new Contact("María García", "655555555", "maria@gmail.com", "Maria company"));

			opportunityRepository.deleteAll();
			opportunityRepository.save(new Opportunity(Product.BOX, 86, contactRepository.findByName("Pepe Lopez"),
					salesRepRepository.findByName("James")));
			opportunityRepository.save(new Opportunity(Product.FLATBED, 186, contactRepository.findByName("Victor Cardozo"),
					salesRepRepository.findByName("James")));
			opportunityRepository.save(new Opportunity(Product.BOX, 446, contactRepository.findByName("Elisa Martínez"),
					salesRepRepository.findByName("Sara")));
			opportunityRepository.save(new Opportunity(Product.BOX, 986, contactRepository.findByName("María García"),
					salesRepRepository.findByName("Julia")));

		}
	}
}
