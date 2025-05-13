package dev.samuel.school_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchoolWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolWebApplication.class, args);
	}

}
