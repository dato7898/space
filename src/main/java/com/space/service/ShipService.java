package com.space.service;

import com.space.model.Ship;

public interface ShipService {

    Iterable getShipList();

    Ship createShip(Ship ship);

    void deleteShip(Long id);

}
