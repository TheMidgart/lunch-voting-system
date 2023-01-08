INSERT INTO users (NAME, EMAIL, PASSWORD)
VALUES ('USER', 'user@gmail.com', '{noop}qwerty123'),
       ('ADMIN', 'admin@admin.com', '{noop}admin'),
       ('ANOTHER_USER', 'anotheruser@ya.ru','{noop}somepassword'),
       ('WORKER', 'worker@sw.com', '{noop}123');

INSERT INTO user_roles (USER_ID, ROLE)
VALUES (100000, 'USER'),
       (100001,'USER'),
       (100001,'ADMIN'),
       (100002, 'USER'),
       (100003, 'USER');

INSERT INTO restaurant (NAME)
VALUES ('Шустрый шмель'),
       ('Хмель'),
       ('Эрик рыжий'),
       ('Розес');

INSERT INTO menu (RESTAURANT_ID, DATE)
VALUES (100004, CURRENT_DATE ),
       (100005, CURRENT_DATE),
       (100006, CURRENT_DATE),
       (100004, DATE_ADD(CURDATE(),INTERVAL 1 DAY)),
       (100005, DATE_ADD(CURDATE(),INTERVAL 1 DAY));

INSERT INTO dish (NAME, PRICE)
VALUES ('Лагер',250),
       ('Стаут', 300),
       ('IPA', 275),
       ('Wizen',200),
       ('Фри', 100),
       ('Клаб-сендвич',300),
       ('Бурито',200);

INSERT INTO dish_menu
VALUES (100013,100008),
       (100017,100008),
       (100014,100009),
       (100018,100009),
       (100015,100009),
       (100016,100010),
       (100019,100011),
       (100018,100011),
       (100013,100012);

INSERT INTO voting_result (USER_ID, MENU_ID)
VALUES (100000, 100008),
       (100001, 100008),
       (100002, 100008),
       (100003, 100008)

