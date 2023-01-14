package com.github.themidgart.web.controller;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.service.RestaurantService;
import com.github.themidgart.to.RestaurantTo;
import com.github.themidgart.util.RestaurantsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "restaurant", description = "Managing dishes, required role ADMIN")
public class RestaurantController {
    public static final String REST_URL = "rest/admin/restaurants";
    @Autowired
    private RestaurantService service;

    @GetMapping
    @Operation(summary = "Get list of all restaurants", tags = {"restaurant"})
    public ResponseEntity<List<RestaurantTo>> getAll() {
        log.info("get all restaurants");
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by id", tags = {"restaurant"})
    ResponseEntity<RestaurantTo> get(@PathVariable int id) {
        log.info("get restaurant  with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Create new restaurant", tags = {"restaurant"})
    public void create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create restaurant {}", restaurantTo);
        service.create(RestaurantsUtil.createFromTo(restaurantTo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update restaurant by id", tags = {"restaurant"})
    public ResponseEntity<Restaurant> update(@PathVariable int id, @Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("update restaurant with id {} : {}", id, restaurantTo);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, restaurantTo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete restaurant by id", tags = {"restaurant"})
    public void delete(@PathVariable int id) {
        log.info("delete restaurant with id {}", id);
        service.delete(id);
    }
}
