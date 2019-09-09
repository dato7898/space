package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public Iterable getShipsByParam(Pageable sortedByParam, String name, String planet, ShipType shipType, Long after,
                                    Long before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                    Integer maxCrewSize, Double minRating, Double maxRating) {
        return this.shipRepository.findAll(sortedByParam.getSort());
    }

    @Override
    public Integer getCount() {
        return (int) this.shipRepository.count();
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
