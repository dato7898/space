package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ShipService {

    Iterable getShipsByParam(ShipOrder order, Integer pageNumber, Integer pageSize, String name, String planet, ShipType shipType, Long after,
                             Long before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                             Integer maxCrewSize, Double minRating, Double maxRating);

    Integer getCount(String name, String planet, ShipType shipType, Long after,
                            Long before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                            Integer maxCrewSize, Double minRating, Double maxRating);

    Ship createShip(Ship ship);

    void deleteShip(Long id);

}
