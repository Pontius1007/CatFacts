package com.example.statnett.demo;

import com.example.statnett.demo.model.CatFact;
import com.example.statnett.demo.repository.CatFactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@SpringBootApplication
public class MyStatnettAPIApplication {
    private static final Logger log = LoggerFactory.getLogger(MyStatnettAPIApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyStatnettAPIApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Profile("!test")
    @Bean
    public CommandLineRunner fetchAndInitDatabase(RestTemplate restTemplate, CatFactRepository catFactRepository) {
        return args -> {
            ResponseEntity<CatFact[]> response = restTemplate.getForEntity(
                    "https://cat-fact.herokuapp.com/facts", CatFact[].class);
            CatFact[] catFacts = response.getBody();

            for (int i = 0; i < Objects.requireNonNull(catFacts).length; i++) {
                log.info("Preloading cat fact " + catFactRepository.save(catFacts[i]));
            }
        };
    }
}
