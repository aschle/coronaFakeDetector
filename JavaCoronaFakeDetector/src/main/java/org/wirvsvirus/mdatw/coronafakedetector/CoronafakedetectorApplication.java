package org.wirvsvirus.mdatw.coronafakedetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan(basePackages = "org.wirvsvirus.mdatw.coronafakedetector")
@SpringBootApplication(scanBasePackages = "org.wirvsvirus.mdatw.coronafakedetector")
public class CoronafakedetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronafakedetectorApplication.class, args);
    }

}
