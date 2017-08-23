package com.shang.spray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * info:
 * Created by shang on 16/7/8.
 */
@SpringBootApplication
public class SprayManageCasApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SprayManageCasApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SprayManageCasApplication.class, args);
    }
}
