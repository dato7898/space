package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public Iterable getShipList() {
        return this.shipRepository.findAll();
    }

    @Override
    public Ship createShip(Ship ship) {
        return this.shipRepository.save(ship);
    }

    @Override
    public void deleteShip(Long id) {
        this.shipRepository.deleteById(id);
    }
}
