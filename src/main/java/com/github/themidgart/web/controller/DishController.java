package com.github.themidgart.web.controller;

import com.github.themidgart.model.Dish;
import com.github.themidgart.service.DishService;
import com.github.themidgart.to.DishTo;
import com.github.themidgart.util.DishesUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "dish", description = "Managing dishes, required role ADMIN")
public class DishController {
    public static final String REST_URL = "rest/admin/dishes";
    private final DishService service;

    @GetMapping
    @Operation(summary = "Get list of all dishes", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    public List<DishTo> getAll() {
        log.info("get all dishes");
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get dish by id", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    public DishTo get(@PathVariable int id) {
        log.info("get dish with id {}", id);
        return service.get(id);
    }

    @PostMapping
    @Operation(summary = "Create new dish", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<Dish> create(@Valid @RequestBody DishTo dishTo) {
        log.info("create dish {}", dishTo);
        Dish created = service.create(DishesUtil.createFromTo(dishTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
