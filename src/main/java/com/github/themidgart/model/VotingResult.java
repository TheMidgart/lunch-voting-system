package com.github.themidgart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "voting_result")
public class VotingResult extends AbstractEntity {
    @ManyToMany
    @NotNull
    private List<User> user;

    @ManyToMany
    @NotNull
    private List<Menu> menu;

}
