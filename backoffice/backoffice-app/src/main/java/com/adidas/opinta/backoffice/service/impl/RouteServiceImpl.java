package com.adidas.opinta.backoffice.service.impl;

import com.adidas.opinta.backoffice.dao.RouteRepository;
import com.adidas.opinta.backoffice.entity.RouteEntity;
import com.adidas.opinta.backoffice.mapper.RouteMapper;
import com.adidas.opinta.backoffice.service.RouteService;
import com.adidas.opinta.model.Route;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;
    private RouteMapper routeMapper;

    public RouteServiceImpl(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    @Override
    public List<Route> findAll() {
        return routeMapper.toDto(routeRepository.findAll());
    }
}
