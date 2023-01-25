package com.github.themidgart.web.controller;

import com.github.themidgart.model.Menu;
import com.github.themidgart.service.MenuService;
import com.github.themidgart.to.DishesForMenuTo;
import com.github.themidgart.to.MenuTo;
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
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "menu", description = "Managing menus, required role ADMIN")
public class MenuController {
    public static final String REST_URL = "rest/admin/menus";
    private final MenuService service;

    @GetMapping
    @Operation(summary = "Get list of all menus", tags = {"menu"}, security = @SecurityRequirement(name = "basicAuth"))
    public List<Menu> getAll() {
        log.info("get all menus");
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get menu by id", tags = {"menu"}, security = @SecurityRequirement(name = "basicAuth"))
    public Menu get(@PathVariable int id) {
        log.info("get menu with id {}", id);
        return service.get(id);
    }

    @PostMapping
    @Operation(summary = "Create new menu", tags = {"menu"}, security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<Menu> create(@Valid @RequestBody MenuTo menuTo) {
        Menu created = service.save(menuTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update menu by id", tags = {"menu"}, security = @SecurityRequirement(name = "basicAuth"))
    public void update(@PathVariable int id, @Valid @RequestBody MenuTo menuTo) {
        log.info("update menu with id {} : {}", id, menuTo);
        service.update(id, menuTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete menu by id", tags = {"menu"}, security = @SecurityRequirement(name = "basicAuth"))
    public void delete(@PathVariable int id) {
        log.info("delete menu with id {}", id);
        service.delete(id);
    }

    @PutMapping("/{id}/menu-items")
    @Operation(summary = "Add dishes to menu with id", tags = {"menu"}, security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<Menu> addDishes(@PathVariable int id, @Valid @RequestBody DishesForMenuTo dishesForMenuTo) {
        log.info("add dishes with id's {} to menu with id {}", dishesForMenuTo, id);
        return ResponseEntity.status(HttpStatus.OK).body(service.addDishes(id, dishesForMenuTo));
    }

    @GetMapping("/filter")
    @Operation(summary = "Get list of menus for any date", tags = {"menu"}, security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<List<Menu>> getAllByDate(@RequestParam(name = "date")
                                                   @NotNull LocalDate date) {
        log.info("get by date {}", date);
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllByDate(date));
    }

}
