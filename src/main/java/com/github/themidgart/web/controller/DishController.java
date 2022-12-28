package com.github.themidgart.web.controller;

import com.github.themidgart.Util.DishesUtil;
import com.github.themidgart.model.Dish;
import com.github.themidgart.service.DishService;
import com.github.themidgart.to.DishTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rest/admin/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService service;

    @GetMapping
    public ResponseEntity<List<Dish>> getAll() {
        log.info("get all dishes");
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Dish> get(@PathVariable int id) {
        log.info("get dish with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody DishTo dishTo) {
        log.info("create dish {}", dishTo);
        service.create(DishesUtil.createFromTo(dishTo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> update(@PathVariable int id, @RequestBody DishTo dishTo) {
        log.info("update dish with id {} : {}", id, dishTo);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dishTo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish with id {}", id);
        service.delete(id);
    }
}
