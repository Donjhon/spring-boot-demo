DROP TABLE IF EXISTS mydemo;
CREATE TABLE mydemo (
  id INTEGER NOT NULL AUTO_INCREMENT,
  myname VARCHAR(1000) DEFAULT NULL,
  age INT(10) DEFAULT NULL,
  KEY `id` (`id`)
);

INSERT INTO mydemo (id,myname,age) VALUES (1,'tom',10);
INSERT INTO mydemo (id,myname,age) VALUES (2,'jack',11);
INSERT INTO mydemo (id,myname,age) VALUES (3,'tim',12);
INSERT INTO mydemo (id,myname,age) VALUES (4,'json',13);