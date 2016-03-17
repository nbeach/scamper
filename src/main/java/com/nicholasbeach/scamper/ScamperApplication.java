package com.nicholasbeach.scamper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class ScamperApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ScamperApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ScamperApplication.class);
    }

}