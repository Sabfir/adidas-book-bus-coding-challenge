package com.adidas.opinta.integration;

import com.adidas.opinta.model.Route;
import java.util.List;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface RouteServiceGateway {
    String CHANNEL_GET_ALL_ROUTES_REQUEST = "getAllRoutes.input";
    String CHANNEL_GET_ALL_ROUTES_REPLY = "getAllRoutes.output";

    @Gateway(requestChannel = CHANNEL_GET_ALL_ROUTES_REQUEST,
            replyChannel = CHANNEL_GET_ALL_ROUTES_REPLY,
            replyTimeout = 10000)
    @Payload("'DUMMY'")
    List<Route> getAllRoutes();

}
