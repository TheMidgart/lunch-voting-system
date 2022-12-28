package com.github.themidgart.web.controller;

import com.github.themidgart.model.Menu;
import com.github.themidgart.service.MenuService;
import com.github.themidgart.to.DishesForMenuTo;
import com.github.themidgart.to.MenuTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rest/admin/menu")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService service;

    @GetMapping
    public ResponseEntity<List<Menu>> getAll() {
        log.info("get all menus");
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Menu> get(@PathVariable int id) {
        log.info("get menu with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody MenuTo menuTo) {
        service.save(menuTo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> update(@PathVariable int id, @RequestBody MenuTo menuTo) {
        log.info("update menu with id {} : {}", id, menuTo);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, menuTo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete menu with id {}", id);
        service.delete(id);
    }

    @PutMapping("/add-dishes/{id}")
    public ResponseEntity<Menu> addDishes(@PathVariable int id, @RequestBody DishesForMenuTo dishesForMenuTo) {
        log.info("add dishes with id's {} to menu with id {}", dishesForMenuTo, id);
        return ResponseEntity.status(HttpStatus.OK).body(service.addDishes(id, dishesForMenuTo));
    }

}
