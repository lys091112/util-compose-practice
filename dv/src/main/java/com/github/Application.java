package com.github;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        Environment env = application.run(args).getEnvironment();

        log.info("\nApplication '{}' is running!\n\t" + "Profile(s): {}\n\t", env.getProperty("spring.application.name"), env.getActiveProfiles());
    }
}
