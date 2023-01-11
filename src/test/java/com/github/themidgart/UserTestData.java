package com.github.themidgart;

import com.github.themidgart.model.User;
import com.github.themidgart.to.UserTo;

public class UserTestData {
    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;
    public static final User USER = new User(USER_ID, "USER", "user@gmail.com", "qwerty123");
    public static final User ADMIN = new User(ADMIN_ID, "ADMIN", "admin@admin.com", "admin");

    public static final UserTo USER_WITH_NOT_UNIQUE_EMAIL = new UserTo(USER_ID, "ANOTHER_USER",
            "admin@admin.com", "admin");

    public static final UserTo NEW_USER = new UserTo(null, "NEW", "some@mail.com", "password");
}