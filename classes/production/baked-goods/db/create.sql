SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS items (
 id int PRIMARY KEY auto_increment,
 itemname VARCHAR,
 itemcategory VARCHAR,
 itemprice DOUBLE
);

CREATE TABLE IF NOT EXISTS carts (
 id int PRIMARY KEY auto_increment,
 carttotal DOUBLE
);

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 address VARCHAR,
 dietarypreference VARCHAR,
 email VARCHAR,
);

CREATE TABLE IF NOT EXISTS sellers (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 address VARCHAR,
 dietarypreference VARCHAR,
 email VARCHAR,
 goodscategory VARCHAR,
);

CREATE TABLE IF NOT EXISTS sellers_items (
 id int PRIMARY KEY auto_increment,
 sellerId INTEGER,
 itemId INTEGER
);

CREATE TABLE IF NOT EXISTS carts_items (
 id int PRIMARY KEY auto_increment,
 sellerId INTEGER,
 cartId INTEGER
);

--CREATE TABLE IF NOT EXISTS buyers (
-- id int PRIMARY KEY auto_increment,
--);
--
--