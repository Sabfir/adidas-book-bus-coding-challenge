package com.adidas.opinta.midoffice.controller;

import com.adidas.opinta.midoffice.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/route")
@Slf4j
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/{departureCity}/{arrivalCity}")
    public ResponseEntity<?> getAllRoutesByDepartureCity(
            @PathVariable("departureCity") String depCityCode,
            @PathVariable("arrivalCity") String arrCityCode) {
        System.out.println("OPINTA: test docker");
        return new ResponseEntity<>(routeService.getBestItinerary(depCityCode, arrCityCode), OK);
    }
}
