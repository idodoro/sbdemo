CREATE TABLE book (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  booktitle varchar(255) DEFAULT NULL,
  writer varchar(255) DEFAULT NULL,
  reader varchar(255) DEFAULT NULL,
  price double DEFAULT NULL,
  des varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE reader (
  username varchar(32) NOT NULL,
  fullname varchar(32) DEFAULT NULL,
  password varchar(32) DEFAULT NULL,
  PRIMARY KEY (username)
);