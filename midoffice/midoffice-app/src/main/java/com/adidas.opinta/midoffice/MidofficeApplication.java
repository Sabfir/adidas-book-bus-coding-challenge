package com.adidas.opinta.midoffice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication(scanBasePackages = {
        "com.adidas.opinta.midoffice",
        "com.adidas.opinta.integration",
        "com.adidas.opinta.common"
})
@EnableIntegration
@IntegrationComponentScan(basePackages = {
        "com.adidas.opinta.integration"
})
@Slf4j
public class MidofficeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MidofficeApplication.class, args);
    }
}
