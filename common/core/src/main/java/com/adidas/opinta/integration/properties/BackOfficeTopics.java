package com.adidas.opinta.integration.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BackOfficeTopics {
    private String allRoutes;
    private String allRoutesByDepartureCite;

    public BackOfficeTopics() {
        // FEATURE: the part of the spring cloud config
        this.allRoutes = "getAllRoutes";
        this.allRoutesByDepartureCite = "getAllRoutesByDepartureCite";
    }
}
