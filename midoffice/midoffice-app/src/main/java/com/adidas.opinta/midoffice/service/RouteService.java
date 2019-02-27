package com.adidas.opinta.midoffice.service;

import com.adidas.opinta.midoffice.dto.Itinerary;

public interface RouteService {

    Itinerary getBestItinerary(String depCityCode, String arrCityCode);
}
