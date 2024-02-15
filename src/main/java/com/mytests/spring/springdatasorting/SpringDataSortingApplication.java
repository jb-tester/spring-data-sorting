package com.mytests.spring.springdatasorting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataSortingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataSortingApplication.class, args);
    }

    @Bean
        public CommandLineRunner commandLineRunner(ItemService service) {
            return args -> {
                service.populateDB();
                System.out.println("--------------------------------------");
                service.offsetLimitTest();
                service.offsetFetchTest();
                System.out.println("--------------------------------------");
            };
        }
}
