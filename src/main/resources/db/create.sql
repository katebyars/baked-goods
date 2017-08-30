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

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 address VARCHAR,
 dietaryPreference VARCHAR,
 email VARCHAR,
);

CREATE TABLE IF NOT EXISTS sellers (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 address VARCHAR,
 dietaryPreference VARCHAR,
 email VARCHAR,
 goodsCategory VARCHAR,
);

CREATE TABLE IF NOT EXISTS buyers (
 id int PRIMARY KEY auto_increment,
  name VARCHAR,
  address VARCHAR,
  dietaryPreference VARCHAR,
  email VARCHAR,
  cartId INTEGER,
);

CREATE TABLE IF NOT EXISTS sellers_items (
 id int PRIMARY KEY auto_increment,
 sellerId INTEGER,
 itemsId INTEGER
);

CREATE TABLE IF NOT EXISTS sellers_carts (
 id int PRIMARY KEY auto_increment,
 sellerId INTEGER,
 cartId INTEGER
);

CREATE TABLE IF NOT EXISTS carts_items (
 id int PRIMARY KEY auto_increment,
 itemsId INTEGER,
 cartId INTEGER
);

