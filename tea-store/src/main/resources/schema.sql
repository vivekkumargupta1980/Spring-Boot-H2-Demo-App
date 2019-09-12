CREATE TABLE user (
  id INT(3) NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE tea (
  id INT(3) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE store_order (
  id INT(3) NOT NULL AUTO_INCREMENT,
  user_id INT(3) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_store_order_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE store_order_item (
  id INT(3) NOT NULL AUTO_INCREMENT,
  store_order_id INT(3) NOT NULL,
  tea_id INT(3) NOT NULL,
  quantity INT(3) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_store_order_item_store_order_id FOREIGN KEY (store_order_id) REFERENCES store_order (id) ON DELETE CASCADE,
  CONSTRAINT FK_store_order_item_tea_id FOREIGN KEY (tea_id) REFERENCES tea (id)
);
