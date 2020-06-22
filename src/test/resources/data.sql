DROP TABLE IF EXISTS `employees`;

CREATE TABLE IF NOT EXISTS `employees`
(
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL,
    age INTEGER NOT NULL,
    CONSTRAINT employees_pk PRIMARY KEY (id)
);

INSERT INTO `employees` VALUES (1,'Stark',35);
INSERT INTO `employees` VALUES (2,'Banner',33);