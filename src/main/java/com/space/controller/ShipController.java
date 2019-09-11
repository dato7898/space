package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;

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

       return this.shipService.getShipsByParam(order, pageNumber, pageSize, name, planet, shipType, after, before,
               isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
    }

    @RequestMapping(value = "/ships/count", method = RequestMethod.GET)
    public @ResponseBody Integer getCount(@RequestParam(value = "name", required = false) String name,
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

        return this.shipService.getCount(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating);
    }

    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    public ResponseEntity createShip(@RequestBody Ship ship) {
        Ship savedShip = this.shipService.createShip(ship);
        if (savedShip == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedShip, HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET)
    public ResponseEntity getShip(@PathVariable Long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Ship> shipOptional = this.shipService.getShip(id);
        if (!shipOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipOptional.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.POST)
    public ResponseEntity updateShip(@PathVariable Long id, @RequestBody Ship ship) {
        Calendar calAfter = Calendar.getInstance();
        calAfter.set(3019, Calendar.JANUARY, 1);
        Calendar calBefore = Calendar.getInstance();
        calBefore.set(2800, Calendar.JANUARY, 1);
        if (id <= 0 || (ship.getName() != null && (ship.getName().length() <= 0 || ship.getName().length() > 50))
                || (ship.getPlanet() != null && (ship.getPlanet().length() <= 0 || ship.getPlanet().length() > 50))
                || ship.getProdDate() != null && (ship.getProdDate().after(calAfter.getTime())
                || ship.getProdDate().before(calBefore.getTime()))
                || ship.getSpeed() != null && (ship.getSpeed() < 0.01 || ship.getSpeed() > 0.99)
                || ship.getCrewSize() != null && (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ship savedShip = this.shipService.updateShip(id, ship);
        if (savedShip == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedShip, HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteShip(@PathVariable(value = "id") Long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!this.shipService.getShip(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.shipService.deleteShip(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
