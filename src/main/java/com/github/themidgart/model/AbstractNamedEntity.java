package com.github.themidgart.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PROTECTED)
public abstract class AbstractNamedEntity extends AbstractEntity{

    @NotBlank
    @Size(min = 2, max = 64)
    @Column(name = "name", nullable = false)
    protected String name;


    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
