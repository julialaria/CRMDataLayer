package com.ironhack.CRMDateLayer;

import com.ironhack.CRMDateLayer.model.SalesRep;
import com.ironhack.CRMDateLayer.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.*;

@SpringBootApplication
public class CrmDateLayerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrmDateLayerApplication.class, args);

	}

	@Component
	public class MyRunner implements CommandLineRunner{

		@Autowired
		private SalesRepRepository salesRepRepository;

		@Override
		public void run(String... args) throws Exception {

			salesRepRepository.deleteAll();
			salesRepRepository.save(new SalesRep("James"));
			salesRepRepository.save(new SalesRep("Sara"));
			salesRepRepository.save(new SalesRep("Michael"));
			salesRepRepository.save(new SalesRep("Julia"));

		}
	}
}
