package com.adidas.opinta.midoffice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    private List<String> shortestRoute;
    private List<String> lessStopsRoute;
}
