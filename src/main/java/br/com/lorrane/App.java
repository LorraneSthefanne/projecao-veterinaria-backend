package br.com.lorrane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EntityScan(basePackages = "br.com.lorrane")
@EnableJpaRepositories(basePackages = "br.com.lorrane.repositories")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
