package com.github.themidgart.web.controller.user;

import com.github.themidgart.model.User;
import com.github.themidgart.service.UserService;
import com.github.themidgart.to.UserTo;
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
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "admin", description = "Managing users, required role ADMIN")
public class AdminUserController {
    public static final String REST_URL = "rest/admin/users";
    private final UserService service;

    @GetMapping
    @Operation(summary = "Get list of all users", tags = {"admin"}, security = @SecurityRequirement(name = "basicAuth"))
    public List<User> getAll() {
        log.info("get all users");
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", tags = {"admin"}, security = @SecurityRequirement(name = "basicAuth"))
    public User getUser(@PathVariable int id) {
        log.info("get user with id {}", id);
        return service.get(id);
    }

    @PostMapping
    @Operation(summary = "Create new user", tags = {"admin"}, security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<User> addUser(@Valid @RequestBody UserTo userTo) {
        log.info("create user {}", userTo);
        User created = service.save(userTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update user by id", tags = {"admin"}, security = @SecurityRequirement(name = "basicAuth"))
    public void updateUser(@PathVariable int id, @Valid @RequestBody UserTo userTo) {
        log.info("update user with id {} {}", id, userTo);
        service.update(id, userTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user by id", tags = {"admin"}, security = @SecurityRequirement(name = "basicAuth"))
    public void deleteUser(@PathVariable int id) {
        log.info("delete user with id " + id);
        service.delete(id);
    }
}
