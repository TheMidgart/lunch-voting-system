INSERT INTO users (NAME, EMAIL, PASSWORD)
VALUES ('USER', 'user@gmail.com', '{noop}qwerty123'),
       ('ADMIN', 'admin@admin.com', '{noop}admin'),
       ('ANOTHER_USER', 'anotheruser@ya.ru', '{noop}somepassword'),
       ('WORKER', 'worker@sw.com', '{noop}123');

INSERT INTO user_roles (USER_ID, ROLE)
VALUES (100000, 'USER'),
       (100001, 'USER'),
       (100001, 'ADMIN'),
       (100002, 'USER'),
       (100003, 'USER');

INSERT INTO restaurant (NAME)
VALUES ('Shustriy shmel'),
       ('Hmel'),
       ('Eric rishiy'),
       ('Rozes');

INSERT INTO menu (RESTAURANT_ID, MENU_DATE)
VALUES (100004, CURRENT_DATE),
       (100005, CURRENT_DATE),
       (100006, CURRENT_DATE),
       (100004, CURRENT_DATE + 1),
       (100005, CURRENT_DATE + 1);

INSERT INTO dish (NAME, PRICE)
VALUES ('Lager', 250),
       ('Stout', 300),
       ('IPA', 275),
       ('Wizen', 200),
       ('Fri', 100),
       ('Club-sandwich', 300),
       ('Burito', 200);

INSERT INTO dish_menu
VALUES (100013, 100008),
       (100017, 100008),
       (100014, 100009),
       (100018, 100009),
       (100015, 100009),
       (100016, 100010),
       (100019, 100011),
       (100018, 100011),
       (100013, 100012);

INSERT INTO vote (USER_ID, MENU_ID)
VALUES (100002, 100008),
       (100003, 100008)

