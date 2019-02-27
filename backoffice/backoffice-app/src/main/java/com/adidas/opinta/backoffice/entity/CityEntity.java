package com.adidas.opinta.backoffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "backoffice", name = "city")
@Data
public class CityEntity {
    @Id
    @Column(columnDefinition = "bpchar", length = 3)
    private String code;
    @Column(length = 128)
    private String name;
}
