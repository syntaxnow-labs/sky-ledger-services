package com.syntaxnow.skyledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Sky Ledger Services Spring Boot application.
 *
 * <p>This class is responsible for bootstrapping and launching the
 * Sky Ledger Services application using Spring Boot's auto-configuration
 * and embedded server support.
 *
 * <p>The {@link org.springframework.boot.autoconfigure.SpringBootApplication}
 * annotation enables:
 * <ul>
 *   <li>Auto-configuration of Spring components</li>
 *   <li>Component scanning within the base package</li>
 *   <li>Configuration support</li>
 * </ul>
 *
 * <p>When the application starts:
 * <ul>
 *   <li>The Spring Application Context is initialized</li>
 *   <li>All beans (Controller, Service, Repository, etc.) are loaded</li>
 *   <li>The embedded server (e.g., Tomcat) is started</li>
 *   <li>REST APIs become available for client requests</li>
 * </ul>
 *
 * <p><strong>Architecture Role:</strong><br>
 * Acts as the root configuration class and application bootstrapper
 * for the Sky Ledger Services – Accounting & Business Management System.
 *
 * <p>Project: Sky Ledger Services – Accounting & Business Management System
 *
 * @author
 *   Korada Jaya Santosh
 * @since 1.0
 */

@SpringBootApplication
public class SkyLedgerServicesApplication {

	public static void main(String[] args) {

        System.out.println("Testing Time Zone : ");
        System.out.println(java.time.ZoneId.systemDefault());
        SpringApplication.run(SkyLedgerServicesApplication.class, args);

	}

}
