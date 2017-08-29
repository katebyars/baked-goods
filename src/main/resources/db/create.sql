SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS items (
 id int PRIMARY KEY auto_increment,
 itemName VARCHAR,
 itemCategory VARCHAR,
 itemPrice DOUBLE
);

--
--CREATE TABLE IF NOT EXISTS buyers (
-- id int PRIMARY KEY auto_increment,
--);
--
--
--CREATE TABLE IF NOT EXISTS sellers (
-- id int PRIMARY KEY auto_increment,
--
--);