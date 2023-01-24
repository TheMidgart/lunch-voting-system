package com.github.themidgart.web.controller;

import com.github.themidgart.service.DishService;
import com.github.themidgart.to.DishTo;
import com.github.themidgart.util.DishesUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "dish", description = "Managing dishes, required role ADMIN")
public class DishController {
    public static final String REST_URL = "rest/admin/dishes";
    @Autowired
    private DishService service;

    @GetMapping
    @Operation(summary = "Get list of all dishes", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<List<DishTo>> getAll() {
        log.info("get all dishes");
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get dish by id", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    ResponseEntity<DishTo> get(@PathVariable int id) {
        log.info("get dish with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Create new dish", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    public void create(@Valid @RequestBody DishTo dishTo) {
        log.info("create dish {}", dishTo);
        service.create(DishesUtil.createFromTo(dishTo));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update dish by id", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    public void update(@PathVariable int id, @Valid @RequestBody DishTo dishTo) {
        log.info("update dish with id {} : {}", id, dishTo);
        service.update(id, dishTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete dish by id", tags = {"dish"}, security = @SecurityRequirement(name = "basicAuth"))
    public void delete(@PathVariable int id) {
        log.info("delete dish with id {}", id);
        service.delete(id);
    }
}
