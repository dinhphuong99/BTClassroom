CREATE DATABASE `bankingmanager`;
USE bankingmanager;
CREATE TABLE customers (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (45) NOT NULL,
    email VARCHAR (45) NOT NULL,
    phone VARCHAR (15) NOT NULL,
    address VARCHAR (45),
    balance DECIMAL(15) DEFAULT 0
);
CREATE TABLE deposits (
	depoId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id INT NOT NULL,
    balance DOUBLE,
    fullday DATETIME,
    FOREIGN KEY (id) REFERENCES customers (id)
);
CREATE TABLE withdraws (
	withId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id INT NOT NULL,
    balance DOUBLE,
    fullday DATETIME,
    FOREIGN KEY (id) REFERENCES customers (id)
);
CREATE TABLE transfers (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    balance DOUBLE,
    fee DOUBLE,
    depoId INT NOT NULL,
    withId INT NOT NULL,
    fullday DATETIME,
    FOREIGN KEY (id) REFERENCES customers (id)
);

