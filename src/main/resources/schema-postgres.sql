DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dish_menu;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq AS INTEGER
    START WITH 100000;

CREATE TABLE users
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name     VARCHAR(64)  NOT NULL,
    email    VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER,
    role    VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(64) NOT NULL
);

CREATE TABLE menu
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER,
    menu_date     DATE                DEFAULT CURRENT_DATE NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE dish
(
    id    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name  VARCHAR(64) NOT NULL,
    price DECIMAL     NOT NULL
);

CREATE TABLE dish_menu
(
    dish_id INTEGER NOT NULL,
    menu_id INTEGER NOT NULL,
    FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE,
    PRIMARY KEY (dish_id, menu_id)
);


CREATE TABLE vote
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id    INTEGER,
    menu_id    INTEGER,
    local_date DATE                DEFAULT CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX uk_vote ON vote (menu_id, user_id);
CREATE UNIQUE INDEX uk_dish_menu ON dish_menu (dish_id, menu_id);
CREATE UNIQUE INDEX uk_user_roles ON user_roles (user_id, role);
CREATE UNIQUE INDEX uk_rest_date ON menu (restaurant_id, menu_date);
CREATE UNIQUE INDEX uk_user_vote_date ON vote (user_id, local_date);