package com.adidas.opinta.backoffice.config;

import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class FlywayConfig {
    private Environment environment;
    private DataSource dataSource;

    @Autowired
    public FlywayConfig(final Environment environment, final DataSource dataSource) {
        this.environment = environment;
        this.dataSource = dataSource;
    }

    @Bean(name = "flyway", initMethod = "migrate")
    public Flyway flyway() {
        final Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("db/migration");
        flyway.setDataSource(dataSource);
        flyway.setSchemas("flyway");
        flyway.setTable("flyway_schema_backoffice");
        // if the problem with checksum or failed migration run app with key -Dflyway.repair=true
        final String repair = environment.getProperty("flyway.repair");
        if ("true".equals(repair)) {
            flyway.repair();
        }
        return flyway;
    }
}
