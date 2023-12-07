package com.iftm.projetofinalnosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ProjetoFinalNoSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoFinalNoSqlApplication.class, args);
    }

}
