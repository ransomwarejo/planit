package com.josias.planit.shared.globalconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class DotEnvConfig {

    static {
        io.github.cdimascio.dotenv.Dotenv.load();
    }
}

