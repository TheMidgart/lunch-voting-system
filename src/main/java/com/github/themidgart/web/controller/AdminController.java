package com.github.themidgart.web.controller;

import com.github.themidgart.model.User;
import com.github.themidgart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rest/admin/users")
@Slf4j
public class AdminController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        log.info("get all users");
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        log.info("get user with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUser(@Valid @RequestBody User user) {
        log.info("create user {}", user);
        service.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        log.info("update user with id {} {}", id, user);
        service.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        log.info("delete user with id " + id);
        service.delete(id);
    }
}
