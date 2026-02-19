package com.syntaxnow.skyledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkyLedgerServicesApplication {

	public static void main(String[] args) {

        System.out.println("Testing TimeZone : ");
        System.out.println(java.time.ZoneId.systemDefault());
        SpringApplication.run(SkyLedgerServicesApplication.class, args);

	}

}
