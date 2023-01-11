DROP TABLE voting_result IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE dish_menu IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE global_seq AS INTEGER
    START WITH 100000;

CREATE TABLE users
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name            VARCHAR(64) NOT NULL,
    email           VARCHAR(128) NOT NULL UNIQUE,
    password        VARCHAR(128) NOT NULL
);

CREATE TABLE user_roles
(
    user_id         INTEGER,
    role            VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name            VARCHAR(64) NOT NULL
);

CREATE TABLE menu
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    restaurant_id   INTEGER NOT NULL,
    date            DATE DEFAULT CURRENT_DATE NOT NULL
);

CREATE TABLE dish
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name            VARCHAR(64) NOT NULL,
    price           DECIMAL NOT NULL
);

CREATE TABLE dish_menu
(
    dish_id         INTEGER NOT NULL FOREIGN KEY REFERENCES dish (id) ON DELETE CASCADE ,
    menu_id         INTEGER NOT NULL FOREIGN KEY REFERENCES menu (id) ON DELETE CASCADE ,
    PRIMARY KEY (dish_id, menu_id)
);


CREATE TABLE voting_result
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    user_id         INTEGER,
    menu_id         INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX uk_voting ON voting_result (menu_id, user_id);
CREATE UNIQUE INDEX uk_dish_menu ON dish_menu(dish_id, menu_id);
CREATE UNIQUE INDEX uk_user_roles ON user_roles(user_id, role);