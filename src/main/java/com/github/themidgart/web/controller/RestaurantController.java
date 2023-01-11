package com.github.themidgart.web.controller;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.service.RestaurantService;
import com.github.themidgart.to.RestaurantTo;
import com.github.themidgart.util.RestaurantsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantController {
    public static final String REST_URL = "rest/admin/restaurants";
    @Autowired
    private RestaurantService service;

    @GetMapping
    public ResponseEntity<List<RestaurantTo>> getAll() {
        log.info("get all restaurants");
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<RestaurantTo> get(@PathVariable int id) {
        log.info("get restaurant  with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create restaurant {}", restaurantTo);
        service.create(RestaurantsUtil.createFromTo(restaurantTo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable int id, @Valid @RequestBody RestaurantTo restaurantTo) {
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
