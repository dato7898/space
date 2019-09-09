package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/rest")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @RequestMapping(value = "/ships", method = RequestMethod.GET)
    public @ResponseBody Iterable getShipList(@RequestParam(value = "name", required = false) String name,
                                              @RequestParam(value = "planet", required = false) String planet,
                                              @RequestParam(value = "shipType", required = false) ShipType shipType,
                                              @RequestParam(value = "after", required = false) Long after,
                                              @RequestParam(value = "before", required = false) Long before,
                                              @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                              @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                              @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                              @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                              @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                              @RequestParam(value = "minRating", required = false) Double minRating,
                                              @RequestParam(value = "maxRating", required = false) Double maxRating,
                                              @RequestParam(value = "order",  defaultValue = "ID") ShipOrder order,
                                              @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                              @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        Pageable sortedByParam = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
        return this.shipService.getShipsByParam(sortedByParam, name, planet, shipType, before, after, isUsed, minSpeed,
                maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
    }

    @RequestMapping(value = "/ships/count", method = RequestMethod.GET)
    public Integer getCount(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "planet", required = false) String planet,
                            @RequestParam(value = "shipType", required = false) ShipType shipType,
                            @RequestParam(value = "after", required = false) Long after,
                            @RequestParam(value = "before", required = false) Long before,
                            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                            @RequestParam(value = "minRating", required = false) Double minRating,
                            @RequestParam(value = "maxRating", required = false) Double maxRating) {
        return this.shipService.getCount();
    }

    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    public Ship createShip(@RequestParam(value = "name") String name,
                                         @RequestParam(value = "planet") String planet,
                                         @RequestParam(value = "shipType") ShipType shipType,
                                         @RequestParam(value = "prodDate") Long prodDate,
                                         @RequestParam(value = "isUsed", defaultValue = "false") Boolean isUsed,
                                         @RequestParam(value = "speed") Double speed,
                                         @RequestParam(value = "crewSize") Integer crewSize) {
        Ship ship = new Ship();
        System.out.println("" + name + planet + shipType + prodDate + isUsed + speed + crewSize);
        ship.setName(name);
        ship.setPlanet(planet);
        ship.setShipType(shipType);
        ship.setProdDate(new Date(prodDate));
        ship.setUsed(isUsed);
        ship.setSpeed(speed);
        ship.setCrewSize(crewSize);
        ship.setRating(10.0);
        return this.shipService.createShip(ship);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE)
    public void deleteShip(@PathVariable(value = "id") Long id) {
        this.shipService.deleteShip(id);
    }

}
