DROP TABLE IF EXISTS role;
CREATE TABLE role(
 role_id  BIGINT NOT NULL AUTO_INCREMENT,
 rolename VARCHAR(20) NOT NULL,
 PRIMARY KEY (role_id)
);

CREATE TABLE account(
 user_id  BIGINT AUTO_INCREMENT NOT NULL,
 username VARCHAR(20) NOT NULL,
 passwd_hash VARCHAR(64),
 role_id  BIGINT NOT NULL,
 PRIMARY KEY (user_id),
 FOREIGN KEY FK_account_role_id (role_id) REFERENCES role(role_id)
);

CREATE TABLE user(
 user_id  BIGINT NOT NULL,
 phone VARCHAR(20),
 first_name VARCHAR(20),
 last_name VARCHAR(20),
 email VARCHAR(20) NOT NULL,
 PRIMARY KEY (user_id),
 FOREIGN KEY FK_user_account_user_id (user_id) REFERENCES account(user_id)
);

CREATE TABLE category(
 category_id BIGINT AUTO_INCREMENT NOT NULL,
 name VARCHAR(60) NOT NULL,
 PRIMARY KEY (category_id)
);

CREATE TABLE product(
 product_id BIGINT AUTO_INCREMENT NOT NULL,
 category_id BIGINT NOT NULL,
 name VARCHAR(60) NOT NULL,
 description VARCHAR(200),
 PRIMARY KEY (product_id),
 FOREIGN KEY FK_product_category_id (category_id) REFERENCES category(category_id)
);

CREATE TABLE price(
 price_id  BIGINT AUTO_INCREMENT NOT NULL,
 product_id BIGINT NOT NULL,
 price  INT NOT NULL ,
 date DATE NOT NULL ,
 PRIMARY KEY (price_id),
 FOREIGN KEY FK_price_product_id (product_id) REFERENCES product(product_id)
);


CREATE TABLE orders(
 order_id  BIGINT AUTO_INCREMENT NOT NULL,
 date DATE NOT NULL,
 number VARCHAR(20),
 user_id  BIGINT NOT NULL,
 PRIMARY KEY (order_id),
 FOREIGN KEY FK_order_user_id (user_id) REFERENCES user(user_id)
);

CREATE TABLE order_item(
 order_item_id  BIGINT AUTO_INCREMENT NOT NULL,
 order_id  BIGINT NOT NULL,
 product_id BIGINT NOT NULL,
 price_id  BIGINT NOT NULL,
 amount SMALLINT NOT NULL,
 PRIMARY KEY (order_item_id),
 FOREIGN KEY FK_order_item_order_id (order_id) REFERENCES orders(order_id),
 FOREIGN KEY FK_order_item_product_id (product_id) REFERENCES product(product_id),
 FOREIGN KEY FK_order_item_price_id (price_id) REFERENCES price(price_id)
);



