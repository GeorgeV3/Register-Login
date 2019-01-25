package gr.dataverse.demoRegLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // turn off/on spring security

public class DemoRegLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRegLogApplication.class, args);
	}

}

