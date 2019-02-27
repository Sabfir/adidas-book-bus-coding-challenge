package com.adidas.opinta.midoffice.service.impl;

import com.adidas.opinta.integration.RouteServiceGateway;
import com.adidas.opinta.midoffice.dto.Itinerary;
import com.adidas.opinta.midoffice.service.RouteService;
import com.adidas.opinta.midoffice.util.BestSellFinder;
import com.adidas.opinta.model.City;
import com.adidas.opinta.model.Route;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {
    private RouteServiceGateway routeServiceGateway;

    public RouteServiceImpl(RouteServiceGateway routeServiceGateway) {
        this.routeServiceGateway = routeServiceGateway;
    }

    @Override
    public Itinerary getBestItinerary(String depCityCode, String arrCityCode) {
        List<Route> routes = routeServiceGateway.getAllRoutes();

        City departureCity = routes.stream()
                .map(Route::getDepartureCity).filter(c -> c.getCode().equals(depCityCode)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can't find city by code " + depCityCode));
        City arrivalCity = routes.stream()
                .map(Route::getArrivalCity).filter(c -> c.getCode().equals(arrCityCode)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can't find city by code " + arrCityCode));

        BestSellFinder algorithm = new BestSellFinder(routes);
        algorithm.execute(departureCity);
        List<City> shortestRoute = algorithm.getPath(arrivalCity);

        routes.forEach(r -> r.setDuration(1));
        algorithm.execute(departureCity);
        List<City> lessStopsRoute = algorithm.getPath(arrivalCity);

        return new Itinerary(
                shortestRoute.stream().map(City::getCode).collect(toList()),
                lessStopsRoute.stream().map(City::getCode).collect(toList()));
    }
}
