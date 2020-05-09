INSERT INTO user(id, email, phoneNumber, name, patronymic, surname)
VALUES (1, 'user1@gmail.com', '+380994776352', 'AAA', 'CCC', 'BBB'),
       (3, 'user3@gmail.com', '+380932847593', 'Nick', 'Igorevich', 'Petrovich');

INSERT INTO account(id, balance, user_id)
VALUES (1, 20000, 1),
       (3, 1500, 3);

INSERT INTO category(dtype, id, name)
VALUES ('Profit', 1, 'salary'),
       ('Consumption', 2, 'shopping');