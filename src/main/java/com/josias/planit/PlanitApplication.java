package com.josias.planit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanitApplication {

	public static void main(String[] args) {
        String profile = System.getProperty("spring.profiles.active", "dev");

        // Load .env ONLY for local profiles
        if (profile.equals("dev") || profile.equals("test")) {
            io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure()
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();

            dotenv.entries().forEach(entry ->
                    System.setProperty(entry.getKey(), entry.getValue()));
        }

        SpringApplication.run(PlanitApplication.class, args);
	}

}
