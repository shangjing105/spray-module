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
public class SprayManageApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SprayManageApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SprayManageApplication.class, args);
    }
}
