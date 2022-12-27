package com.github.themidgart.controller;

import com.github.themidgart.Util.RestaurantsUtil;
import com.github.themidgart.model.Restaurant;
import com.github.themidgart.service.RestaurantService;
import com.github.themidgart.to.RestaurantTo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/rest/admin/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Restaurant> get(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody RestaurantTo restaurantTo) {
        service.create(RestaurantsUtil.createFromTo(restaurantTo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable int id, @RequestBody RestaurantTo restaurantTo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id,restaurantTo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
