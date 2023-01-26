package com.github.themidgart.web.controller;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.service.RestaurantService;
import com.github.themidgart.to.RestaurantTo;
import com.github.themidgart.util.RestaurantsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "restaurant", description = "Managing dishes, required role ADMIN")
public class RestaurantController {
    public static final String REST_URL = "rest/admin/restaurants";
    private final RestaurantService service;

    @GetMapping
    @Operation(summary = "Get list of all restaurants", tags = {"restaurant"}, security = @SecurityRequirement(name = "basicAuth"))
    public List<RestaurantTo> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by id", tags = {"restaurant"}, security = @SecurityRequirement(name = "basicAuth"))
    RestaurantTo get(@PathVariable int id) {
        log.info("get restaurant  with id {}", id);
        return service.get(id);
    }

    @PostMapping
    @Operation(summary = "Create new restaurant", tags = {"restaurant"}, security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<Restaurant> create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create restaurant {}", restaurantTo);
        Restaurant created = service.create(RestaurantsUtil.createFromTo(restaurantTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update restaurant by id", tags = {"restaurant"}, security = @SecurityRequirement(name = "basicAuth"))
    public void update(@PathVariable int id, @Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("update restaurant with id {} : {}", id, restaurantTo);
        service.update(id, restaurantTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete restaurant by id", tags = {"restaurant"}, security = @SecurityRequirement(name = "basicAuth"))
    public void delete(@PathVariable int id) {
        log.info("delete restaurant with id {}", id);
        service.delete(id);
    }
}
