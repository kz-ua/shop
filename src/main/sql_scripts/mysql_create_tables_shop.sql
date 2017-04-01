DROP TABLE IF EXISTS role;


CREATE TABLE account(
 id  BIGINT AUTO_INCREMENT NOT NULL,
 username VARCHAR(20) NOT NULL,
 password VARCHAR(64),
 role  VARCHAR(32) NOT NULL DEFAULT 'guest',
 version BIGINT NOT NULL DEFAULT 0,
 user_id BIGINT,
 PRIMARY KEY (id)
);

CREATE TABLE user(
 id  BIGINT AUTO_INCREMENT NOT NULL,
 phone VARCHAR(20),
 first_name VARCHAR(20),
 last_name VARCHAR(20),
 email VARCHAR(20) NOT NULL,
 version BIGINT NOT NULL DEFAULT 0,
 PRIMARY KEY (id)
);

CREATE TABLE category(
 id BIGINT AUTO_INCREMENT NOT NULL,
 name VARCHAR(60) NOT NULL,
 version BIGINT NOT NULL DEFAULT 0,
 PRIMARY KEY (id)
);

CREATE TABLE product(
 id BIGINT AUTO_INCREMENT NOT NULL,
 category_id BIGINT NOT NULL,
 name VARCHAR(60) NOT NULL,
 description VARCHAR(200),
 version BIGINT NOT NULL DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY FK_product_category_id (category_id) REFERENCES category(id)
);

CREATE TABLE price(
 id  BIGINT AUTO_INCREMENT NOT NULL,
 product_id BIGINT NOT NULL,
 price  INT NOT NULL ,
 date DATE NOT NULL ,
 version BIGINT NOT NULL DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY FK_price_product_id (product_id) REFERENCES product(id)
);


CREATE TABLE orders(
 id  BIGINT AUTO_INCREMENT NOT NULL,
 date DATE NOT NULL,
 number VARCHAR(20),
 user_id  BIGINT NOT NULL,
 version BIGINT NOT NULL DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY FK_order_user_id (user_id) REFERENCES user(id)
);

CREATE TABLE order_item(
 id  BIGINT AUTO_INCREMENT NOT NULL,
 order_id  BIGINT NOT NULL,
 product_id BIGINT NOT NULL,
 price_id  BIGINT NOT NULL,
 amount SMALLINT NOT NULL,
 version BIGINT NOT NULL DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY FK_order_item_order_id (order_id) REFERENCES orders(id),
 FOREIGN KEY FK_order_item_product_id (product_id) REFERENCES product(id),
 FOREIGN KEY FK_order_item_price_id (price_id) REFERENCES price(id)
);



