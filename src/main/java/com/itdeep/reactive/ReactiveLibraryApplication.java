package com.itdeep.reactive;

import com.itdeep.reactive.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ReactiveLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveLibraryApplication.class, args);
    }

}
