package com.github.themidgart.controller;

import com.github.themidgart.Util.RestaurantsUtil;
import com.github.themidgart.model.Restaurant;
import com.github.themidgart.service.RestaurantService;
import com.github.themidgart.to.RestaurantTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rest/admin/restaurant")
@Slf4j
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("get all restaurants");
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get restaurant  with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody RestaurantTo restaurantTo) {
        log.info("create restaurant {}", restaurantTo);
        service.create(RestaurantsUtil.createFromTo(restaurantTo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable int id, @RequestBody RestaurantTo restaurantTo) {
        log.info("update restaurant with id {} : {}", id, restaurantTo);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, restaurantTo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant with id {}", id);
        service.delete(id);
    }
}
