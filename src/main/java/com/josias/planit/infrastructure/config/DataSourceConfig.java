package com.josias.planit.infrastructure.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.josias.planit.infrastructure.persistence.jpa")
@EnableJpaRepositories(basePackages = "com.josias.planit.infrastructure.persistence.jpa")
public class DataSourceConfig {
}
