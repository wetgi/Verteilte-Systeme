INSERT INTO eshop.role (`id`, `level`, `type`) values(1, 0, 'admin');
INSERT INTO eshop.role (`id`, `level`, `type`) values(2, 1, 'user');


INSERT INTO eshop.user (`user_id`, `firstname`, `level`, `name`, `password`, `username`) 
VALUES (0, 'Rob',  0, 'Heisenberg', 'admin', 'admin');
INSERT INTO eshop.user (`user_id`, `firstname`, `level`, `name`, `password`, `username`) 
VALUES (1, 'Max',  1, 'Mustermann', 'user1', 'user1');