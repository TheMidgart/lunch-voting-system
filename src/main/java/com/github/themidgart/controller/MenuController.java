package com.github.themidgart.controller;

import com.github.themidgart.model.Menu;
import com.github.themidgart.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rest/admin/menu")

public class MenuController {
    @Autowired
    private MenuService service;

    @GetMapping
    public ResponseEntity<List<Menu>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Menu> get(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Menu menu){
        service.save(menu);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody Menu menu){
        service.update(id,menu);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
