
DROP DATABASE IF EXISTS TWITTER;
CREATE DATABASE TWITTER;

USE TWITTER;

DROP USER IF EXISTS evang@localhost;
CREATE USER evang@'localhost' IDENTIFIED WITH mysql_native_password BY 'dawson' REQUIRE NONE;
GRANT ALL ON TWITTER.* TO evang@'localhost';

FLUSH PRIVILEGES;
