package com.github.themidgart.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "voting_result")
public class VotingResult extends AbstractEntity {

    //many-to-many https://www.baeldung.com/jpa-many-to-many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonManagedReference
    @NotNull
    private Menu menu;

    public VotingResult(Integer id, User user, Menu menu) {
        super(id);
        this.user = user;
        this.menu = menu;
    }
}
