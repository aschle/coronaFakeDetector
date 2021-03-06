package org.wirvsvirus.mdatw.coronafakedetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.wirvsvirus.mdatw.coronafakedetector.common.FileStorageProperties;

@EnableJpaRepositories
@EntityScan(basePackages = "org.wirvsvirus.mdatw.coronafakedetector")
@SpringBootApplication(scanBasePackages = "org.wirvsvirus.mdatw.coronafakedetector")
@EnableConfigurationProperties({FileStorageProperties.class})
public class CoronafakedetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronafakedetectorApplication.class, args);
    }

}
