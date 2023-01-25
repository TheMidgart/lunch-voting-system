package com.github.themidgart.web.controller.user;

import com.github.themidgart.model.User;
import com.github.themidgart.service.UserService;
import com.github.themidgart.to.UserTo;
import com.github.themidgart.web.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "profile", description = "Managing user self data")
public class ProfileController {
    static final String REST_URL = "/rest/profile";
    private final UserService service;

    @GetMapping
    @Operation(summary = "Get data", tags = {"profile"}, security = @SecurityRequirement(name = "basicAuth"))
    public User get(@AuthenticationPrincipal AuthUser user) {
        log.info("get {}", user);
        return user.getUser();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete profile", tags = {"profile"}, security = @SecurityRequirement(name = "basicAuth"))
    public void delete(@AuthenticationPrincipal AuthUser user) {
        service.delete(user.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update data", tags = {"profile"}, security = @SecurityRequirement(name = "basicAuth"))
    public void update(@RequestBody @Valid UserTo userTo, @AuthenticationPrincipal AuthUser user) {
        log.info("update {} with id={}", userTo, user.id());
        service.update(user.id(), userTo);
    }

}
