package com.adidas.opinta.midoffice.util;

import com.adidas.opinta.model.City;
import com.adidas.opinta.model.Route;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Slf4j
public class BestSellFinder {
    private List<Route> routes;
    private Set<City> settledNodes;
    private Set<City> unSettledNodes;
    private Map<City, City> predecessors;
    private Map<City, Integer> durations;

    public BestSellFinder(List<Route> routes) {
        this.routes = new ArrayList<>(routes);
    }

    public void execute(City departureCity) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        durations = new HashMap<>();
        predecessors = new HashMap<>();
        durations.put(departureCity, 0);
        unSettledNodes.add(departureCity);
        while (unSettledNodes.size() > 0) {
            City node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDuration(node);
        }
    }

    public List<City> getPath(City target) {
        List<City> path = new ArrayList<>();
        City step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return emptyList();
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    private void findMinimalDuration(City node) {
        List<City> adjacentNodes = getNeighbors(node);
        for (City target : adjacentNodes) {
            if (getShortestDistance(target) > (getShortestDistance(node) + getDistance(node, target))) {
                durations.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    private int getDistance(City from, City to) {
        return routes.stream()
                .filter(route -> route.getDepartureCity().equals(from) && route.getArrivalCity().equals(to))
                .map(Route::getDuration)
                .findAny().orElseThrow(() -> new RuntimeException("Should not happen"));
    }

    private List<City> getNeighbors(City city) {
        return routes.stream()
                .filter(route -> route.getDepartureCity().equals(city) && !isSettled(route.getArrivalCity()))
                .map(Route::getArrivalCity)
                .collect(toList());
    }

    private boolean isSettled(City city) {
        return settledNodes.contains(city);
    }

    private City getMinimum(Set<City> cities) {
        City minimum = null;
        for (City city : cities) {
            if (minimum == null) {
                minimum = city;
            } else {
                if (getShortestDistance(city) < getShortestDistance(minimum)) {
                    minimum = city;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(City arrivalCity) {
        Integer duration = durations.get(arrivalCity);
        if (duration == null) {
            return Integer.MAX_VALUE;
        } else {
            return duration;
        }
    }
}
