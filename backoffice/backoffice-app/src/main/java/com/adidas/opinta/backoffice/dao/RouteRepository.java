package com.adidas.opinta.backoffice.dao;

import com.adidas.opinta.backoffice.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {
}
