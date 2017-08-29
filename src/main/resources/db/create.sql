SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS items (
 id int PRIMARY KEY auto_increment,
 itemName VARCHAR,
 itemCategory VARCHAR,
 itemPrice DOUBLE
);

CREATE TABLE IF NOT EXISTS carts (
 id int PRIMARY KEY auto_increment,
 cartTotal DOUBLE
);

CREATE TABLE IF NOT EXISTS sellers (
 id int PRIMARY KEY auto_increment,
 cartTotal DOUBLE,
 name VARCHAR,
 address VARCHAR,
 email VARCHAR,
 goodsCategory VARCHAR
);


--CREATE TABLE IF NOT EXISTS buyers (
-- id int PRIMARY KEY auto_increment,
--);
--
--