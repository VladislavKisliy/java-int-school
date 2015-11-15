--fill genres
insert into BOOK_GENRES values('HORROR');
insert into BOOK_GENRES values('HUMOR');
insert into BOOK_GENRES values('ROMANCE');
insert into BOOK_GENRES values('HEALTH');
insert into BOOK_GENRES values('ADVENTURE');
insert into BOOK_GENRES values('FANTASY');
insert into BOOK_GENRES values('NONFICTION');
insert into BOOK_GENRES values('LITERATURE');
insert into BOOK_GENRES values('NOVEL');
insert into BOOK_GENRES values('BUSINESS');
commit;
select * from BOOK_GENRES;
--fill books
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Peter Straub','Koko','Some horror story','111',to_date('10.12.1997','dd.mm.yyyy'),'HORROR',95);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Don Colbert','The Seven Pillars of Health','Some health story','222',to_date('10.12.1987','dd.mm.yyyy'),'HEALTH',81);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('J.K. Rowling','Harry Potter and the Prisoner of Azkaban','Some Harry Potter story','333',to_date('29.08.1999','dd.mm.yyyy'),'FANTASY',93);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Joseph Heller','Catch 22','Some literature story','444',to_date('06.09.2009','dd.mm.yyyy'),'LITERATURE',80);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('The Odyssey','Homer','Some adventure story','555',to_date('05.05.2011','dd.mm.yyyy'),'ADVENTURE',55);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Diana Gabaldon','Outlander','Some romance story','555',to_date('09.03.1995','dd.mm.yyyy'),'ROMANCE',65);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Sun Tzu','The Art of War','Some business story','123',to_date('04.10.2005','dd.mm.yyyy'),'BUSINESS',45);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Ivan Ivanovich','HotSpot','Some story','4356',to_date('23.02.2014','dd.mm.yyyy'),'HORROR',30);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Oleg Ivanov','RoKoko','Some humor story','435345',to_date('10.01.2008','dd.mm.yyyy'),'HUMOR',85);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE)
values ('John Black','White horse','Some literature story','123',to_date('12.07.1997','dd.mm.yyyy'),'LITERATURE');
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('William Le Queux','The House of Whispers','Some romance story','222',to_date('10.12.1987','dd.mm.yyyy'),'ROMANCE',81);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Charlotte Bronte','Jane Eyre','Some romance story','222',to_date('10.12.1987','dd.mm.yyyy'),'ROMANCE',81);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Erich Maria Remarque','Three Comrades','Novel story','222',to_date('10.12.1987','dd.mm.yyyy'),'NOVEL',100);
insert into books (AUTHOR,TITLE,SHORT_DESCRIPTION,PUBLISHING_DEPT,RELEASED,GENERE,RATING)
values ('Don Colbert','The Seven Pillars of Health','Some health story','222',to_date('10.12.1987','dd.mm.yyyy'),'HEALTH',81);
commit;
select * from OTOPORKOV.BOOKS;
desc OTOPORKOV.BOOKS;
desc otoporkov.users;
--fill users
insert into OTOPORKOV.USERS (USERNAME,SECOND_NAME,DATE_OF_BURTH,SEX)
values ('Oleg','Toporkov',to_date('10.11.1989','dd.mm.yyyy'),'M');
insert into OTOPORKOV.USERS (USERNAME,SECOND_NAME,DATE_OF_BURTH,SEX)
values ('Vladislav','Kisliy',to_date('21.03.1977','dd.mm.yyyy'),'M');
insert into OTOPORKOV.USERS (USERNAME,SECOND_NAME,DATE_OF_BURTH,SEX)
values ('Escender','Sary',to_date('04.10.1987','dd.mm.yyyy'),'M');
insert into OTOPORKOV.USERS (USERNAME,SECOND_NAME,DATE_OF_BURTH,SEX)
values ('Dmitry','Kuzmin',to_date('30.08.1978','dd.mm.yyyy'),'M');
commit;
select * from OTOPORKOV.USERS;
