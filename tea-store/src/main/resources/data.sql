INSERT INTO user
  (id, firstname, lastname, email)
VALUES
  (1, 'Bob', 'Johnson', 'bob.johnson@lixar.test'),
  (2, 'Dan', 'Bradley', 'dan.bradley@lixar.test');


INSERT INTO tea
  (id, name, price)
VALUES
  (1, 'Green tea', 2.50),
  (2, 'Black tea', 2.00),
  (3, 'Earl Grey tea', 3.00);

INSERT INTO store_order
  (id, user_id, created_at)
VALUES
  (1, 1, CURRENT_TIMESTAMP),
  (2, 1, CURRENT_TIMESTAMP),
  (3, 2, CURRENT_TIMESTAMP);

INSERT INTO store_order_item
  (id, store_order_id, tea_id, quantity)
VALUES
  (1, 1, 1, 10),
  (2, 1, 2, 10),
  (3, 1, 3, 10),
  (4, 2, 1, 20),
  (5, 3, 2, 1),
  (6, 3, 3, 2);
