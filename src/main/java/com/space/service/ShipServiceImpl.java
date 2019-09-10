package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public Iterable getShipsByParam(ShipOrder order, Integer pageNumber, Integer pageSize, String name, String planet,
                                    ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed,
                                    Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {

        List<Ship> shipList = getShipByParam(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
        shipList = getShipByPage(pageNumber, pageSize, shipList);
        shipList = getShipByOrder(order, shipList);
        return shipList;
    }

    @Override
    public Integer getCount(String name, String planet, ShipType shipType, Long after,
                            Long before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                            Integer maxCrewSize, Double minRating, Double maxRating) {

        List<Ship> shipList = getShipByParam(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
        return shipList.size();
    }

    private List<Ship> getShipByParam(String name, String planet, ShipType shipType, Long after, Long before,
                                      Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                      Integer maxCrewSize, Double minRating, Double maxRating) {
        List<Ship> shipList = this.shipRepository.findAll();
        if (name != null && name.length() != 0) {
            shipList = getShipByName(name, shipList);
        }
        if (planet != null && planet.length() != 0) {
            shipList = getShipByPlanet(planet, shipList);
        }
        if (shipType != null) {
            shipList = getShipByShipType(shipType, shipList);
        }
        if (before != null && before >= 0) {
            shipList = getShipByBefore(before, shipList);
        }
        if (after != null && after >= 0) {
            shipList = getShipByAfter(after, shipList);
        }
        if (isUsed != null) {
            shipList = getShipByIsUsed(isUsed, shipList);
        }
        if (minSpeed != null && minSpeed >= 0) {
            shipList = getShipByMinSpeed(minSpeed, shipList);
        }
        if (maxSpeed != null && maxSpeed >= 0) {
            shipList = getShipByMaxSpeed(maxSpeed, shipList);
        }
        if (minCrewSize != null && minCrewSize >= 0) {
            shipList = getShipByMinCrewSize(minCrewSize, shipList);
        }
        if (maxCrewSize != null && maxCrewSize >= 0) {
            shipList = getShipByMaxCrewSize(maxCrewSize, shipList);
        }
        if (minRating != null && minRating >= 0) {
            shipList = getShipByMinRating(minRating, shipList);
        }
        if (maxRating != null && maxRating >= 0) {
            shipList = getShipByMaxRating(maxRating, shipList);
        }
        return shipList;
    }

    @Override
    public Ship createShip(String name, String planet, ShipType shipType, Long prodDate, Boolean isUsed, Double speed, Integer crewSize) {
        return null;
    }

    @Override
    public void deleteShip(Long id) {
        this.shipRepository.deleteById(id);
    }

    public List<Ship> getShipByName(String name, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getName().contains(name)) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByPlanet(String planet, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getPlanet().contains(planet)) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByShipType(ShipType type, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getShipType() == type) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByAfter(Long after, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getProdDate().getTime() >= after) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByBefore(Long before, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getProdDate().getTime() <= before) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByIsUsed(Boolean isUsed, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getUsed() == isUsed) {
                result.add(ship);
            }
        }
        return result;
    }


    public List<Ship> getShipByMinSpeed(Double minSpeed, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getSpeed() >= minSpeed) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByMaxSpeed(Double maxSpeed, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getSpeed() <= maxSpeed) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByMinCrewSize(Integer minCrewSize, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getCrewSize() >= minCrewSize) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByMaxCrewSize(Integer maxCrewSize, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getCrewSize() <= maxCrewSize) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByMinRating(Double minRating, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getRating() >= minRating) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByMaxRating(Double maxRating, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getRating() <= maxRating) {
                result.add(ship);
            }
        }
        return result;
    }

    public List<Ship> getShipByOrder(ShipOrder order, List<Ship> ships) {
        if (order == ShipOrder.ID) {
            ships.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        } else if (order == ShipOrder.DATE) {
            ships.sort((o1, o2) -> (int) (o1.getProdDate().getTime() - o2.getProdDate().getTime()));
        } else if (order == ShipOrder.SPEED) {
            ships.sort((o1, o2) -> {
                if (o1.getSpeed() > o2.getSpeed())
                    return 1;
                else if (o1.getSpeed().equals(o2.getSpeed()))
                    return 0;
                else
                    return -1;
            });
        } else if (order == ShipOrder.RATING) {
            ships.sort((o1, o2) -> {
                if (o1.getRating() > o2.getRating())
                    return 1;
                else if (o1.getRating().equals(o2.getRating()))
                    return 0;
                else
                    return -1;
            });
        }

        return ships;
    }


    public List<Ship> getShipByPage(Integer pageNumber, Integer pageSize, List<Ship> ships) {
        int skip = pageNumber * pageSize;
        List<Ship> result = new ArrayList<>();
        for (int i = skip; i < Math.min(skip + pageSize, ships.size()); i++) {
            result.add(ships.get(i));
        }
        return result;
    }

}
