package com.ironhack.CRMDateLayer;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.*;

@SpringBootApplication
public class CrmDateLayerApplication {

    public static void main(String[] args) {
       // SpringApplication.run(CrmDateLayerApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(CrmDateLayerApplication.class);
    }
}


