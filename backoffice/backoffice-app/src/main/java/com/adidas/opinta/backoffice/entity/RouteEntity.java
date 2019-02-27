package com.adidas.opinta.backoffice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(schema = "backoffice", name = "route")
@Data
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "route_generator")
    @SequenceGenerator(name = "route_generator", sequenceName = "route_sequence",
            schema = "backoffice", allocationSize = 1)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "departure_city")
    private CityEntity departureCity;
    @ManyToOne
    @JoinColumn(name = "arrival_city")
    private CityEntity arrivalCity;
    private int duration;
}
