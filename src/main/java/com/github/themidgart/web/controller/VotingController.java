package com.github.themidgart.web.controller;

import com.github.themidgart.model.Menu;
import com.github.themidgart.service.MenuService;
import com.github.themidgart.service.VotingService;
import com.github.themidgart.to.VotingResultTo;
import com.github.themidgart.web.security.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VotingController {
    public static final String REST_URL = "rest/profile/voting";
    @Autowired
    private VotingService votingService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getVotingOptionsByDate(@RequestParam(name = "date")
                                                             @NotNull LocalDate date) {
        log.info("get options to choose on date {}", date);
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getAllByDate(date));
    }


    @GetMapping("/vote/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void vote(@PathVariable int id) {
        AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        votingService.vote(id, user.id());
    }

    @GetMapping("/results")
    public ResponseEntity<VotingResultTo> getResultsByDate(@RequestParam(name = "date")
                                                           @NotNull LocalDate date) {
        log.info("get options to choose on date {}", date);
        return ResponseEntity.status(HttpStatus.OK).body(votingService.getResultsByDate(date));
    }
}
