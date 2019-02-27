package com.adidas.opinta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private Integer id;
    private City departureCity;
    private City arrivalCity;
    private int duration;
}
