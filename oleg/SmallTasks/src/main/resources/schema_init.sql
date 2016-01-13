create table OTOPORKOV.BOOK_GENRES
(
GENRE VARCHAR2(25) CONSTRAINT PK_GENRE PRIMARY KEY
);
create table OTOPORKOV.BOOKS
(
BOOK_ID NUMBER(10) CONSTRAINT PK_BOOK_ID  PRIMARY KEY,
AUTHOR varchar2(150) NOT NULL,
TITLE varchar2(200) NOT NULL,
SHORT_DESCRIPTION varchar2(2000),
PUBLISHING_DEPT varchar2(100) NOT NULL,
RELEASED date NOT NULL,
GENERE varchar2(25) CONSTRAINT FK_GENRE  REFERENCES  BOOK_GENRES(GENRE),
RATING number(3) DEFAULT 0
);
create table OTOPORKOV.USERS
(
USER_ID number(10) CONSTRAINT PK_USER_ID  PRIMARY KEY,
USERNAME varchar2(100) NOT NULL,
SECOND_NAME varchar2(100) NOT NULL,
DATE_OF_BURTH date NOT NULL,
REGISTERED DATE DEFAULT SYSDATE ,
SEX VARCHAR2(1)  CONSTRAINT CHK_SEX_FM CHECK (SEX in ('m','M','f','F'))
);
create table OTOPORKOV.ACTIVE_READERS
	(
	ID number (4) CONSTRAINT PK_ID  PRIMARY KEY,
	BOOK_ID NUMBER(10) CONSTRAINT FK_BOOK_ID  REFERENCES  BOOKS(BOOK_ID),
	USER_ID NUMBER(10) CONSTRAINT FK_USER_ID  REFERENCES  USERS(USER_ID),
	DATE_GOT DATE DEFAULT SYSDATE
	)
;

CREATE SEQUENCE BOOKS_SEQ;
CREATE SEQUENCE USERS_SEQ;
CREATE SEQUENCE ACTIVE_READERS_SEQ;

CREATE OR REPLACE TRIGGER Z_BOOKS_I
BEFORE INSERT ON BOOKS
FOR EACH ROW

BEGIN
  SELECT BOOKS_SEQ.NEXTVAL
  INTO   :new.BOOK_ID
  FROM   dual;
END;
/
CREATE OR REPLACE TRIGGER Z_USERS_I
BEFORE INSERT ON USERS
FOR EACH ROW

BEGIN
  SELECT USERS_SEQ.NEXTVAL
  INTO   :new.USER_ID
  FROM   dual;
END;
/
CREATE OR REPLACE TRIGGER Z_AR_I
BEFORE INSERT ON ACTIVE_READERS
FOR EACH ROW

BEGIN
  SELECT ACTIVE_READERS_SEQ.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;
/

--drop table OTOPORKOV.ACTIVE_READERS;
--drop table OTOPORKOV.BOOKS;
--drop table OTOPORKOV.USERS;
--drop table OTOPORKOV.BOOK_GENERES;     
--drop trigger Z_BOOKS_I;
--drop trigger Z_USERS_I;
--drop trigger Z_AR_I;
--drop SEQUENCE BOOKS_SEQ;
--drop SEQUENCE USERS_SEQ;
--drop SEQUENCE ACTIVE_READERS_SEQ;