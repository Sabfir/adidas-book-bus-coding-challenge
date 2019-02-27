package com.adidas.opinta.backoffice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {
        "com.adidas.opinta.backoffice",
        "com.adidas.opinta.integration.properties",
        "com.adidas.opinta.common"
})
@EnableIntegration
@EnableKafka
@EnableTransactionManagement
@Slf4j
public class BackOfficeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BackOfficeApplication.class);
    }
}
