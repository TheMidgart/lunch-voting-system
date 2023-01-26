package com.github.themidgart.web.controller;

import com.github.themidgart.model.Menu;
import com.github.themidgart.service.MenuService;
import com.github.themidgart.service.VoteService;
import com.github.themidgart.to.VoteTo;
import com.github.themidgart.web.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "vote", description = "Vote operations")
@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class VoteController {
    public static final String REST_URL = "rest/votes";
    private final VoteService voteService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/options")
    @Operation(summary = "Show options to vote on certain date, without param - today", tags = {"vote"},
            security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<List<Menu>> getVoteOptionsByDate(@RequestParam(name = "date", required = false)
                                                           @Nullable LocalDate date) {
        if (date == null) date = LocalDate.now();
        log.info("get options to choose on date {}", date);
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getAllByDate(date));
    }


    @PostMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Vote for restaurant with id, authorisation required", tags = {"vote"},
            security = @SecurityRequirement(name = "basicAuth"))
    public void vote(@PathVariable int restaurantId, @RequestParam(name = "date", required = false)
    @Nullable LocalDate date, @AuthenticationPrincipal AuthUser user) {
        if (date == null) date = LocalDate.now();
        voteService.vote(restaurantId, user.id(), date);
    }

    /*Leave this method
     *Change access only for admin
     *Change map to List<Object[]> for use it on frontend*/

    @GetMapping("/admin/results")
    @Operation(summary = "Show vote results on certain date, without param - today, required role ADMIN", tags = {"vote"},
            security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<VoteTo> getResultsByDate(@RequestParam(name = "date", required = false)
                                                   @Nullable LocalDate date) {
        if (date == null) date = LocalDate.now();
        log.info("get options to choose on date {}", date);
        return ResponseEntity.status(HttpStatus.OK).body(voteService.getResultsByDate(date));
    }
}
