package com.github.themidgart.to;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class UserTo {
    Integer id;
    @NotNull
    @Size(min = 2, max = 64)
    String name;

    @Email
    @NotBlank
    @Size(min = 5, max = 128)
    String email;

    @NotBlank
    @Size(min = 5, max = 128)
    String password;


    public UserTo(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo:" + id + '[' + email + ']';
    }
}
