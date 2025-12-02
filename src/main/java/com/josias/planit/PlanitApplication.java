package com.josias.planit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanitApplication {

	public static void main(String[] args) {
        String env = System.getProperty("spring.profiles.active", "dev");

        if (env.equals("dev") || env.equals("test")) {
            io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.load();

            // On pousse dans System.getenv → Spring pourra lire les var
            dotenv.entries().forEach(e ->
                    System.setProperty(e.getKey(), e.getValue()));
        }

        SpringApplication.run(PlanitApplication.class, args);
	}

}
