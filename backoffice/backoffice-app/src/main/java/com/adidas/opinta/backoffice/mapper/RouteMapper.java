package com.adidas.opinta.backoffice.mapper;

import com.adidas.opinta.backoffice.entity.RouteEntity;
import com.adidas.opinta.mapping.GenericMapper;
import com.adidas.opinta.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper extends GenericMapper<RouteEntity, Route> {
}
