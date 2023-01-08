package com.github.themidgart.util;

import com.github.themidgart.model.User;
import com.github.themidgart.model.UserRole;
import com.github.themidgart.to.UserTo;
import lombok.experimental.UtilityClass;

import static com.github.themidgart.config.SecurityConfig.PASSWORD_ENCODER;

@UtilityClass
public class UsersUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), UserRole.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

}
