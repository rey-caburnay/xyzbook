
truncate table book_dtls restart identity;
truncate table book_author restart identity;
truncate table author restart identity;
truncate table publisher restart identity;
truncate table book restart identity;



insert into author(id, firstname, lastname, middlename)
values(1,'Joel', 'Hartse','');
insert into author(id, firstname, lastname, middlename)
values(2,'Hanna', 'Templer', 'P');
insert into author(id, firstname, lastname, middlename)
values(3,'Marguerite', 'Duras', 'Z');
insert into author(id, firstname, lastname, middlename)
values(4,'Kingsley', 'Amis','');
insert into author(id, firstname, lastname, middlename)
values(5,'Fannie', 'Flagg', 'Peters');
insert into author(id, firstname, lastname, middlename)
values(6,'Camille','Paglia', 'Byron');
insert into author(id, firstname, lastname, middlename)
values(7,'Rainer', 'Rilke', 'Steel');

--insert into book(id, title, isbn_13, isbn_10)
--values(1,'American Elf', '978-1-891830-85-3', '1-891-83085-6');
--insert into book(id, title, isbn_13, isbn_10)
--values(2,'Cosmoknights', '978-1-60309-454-2', '1-603-09454-7');
--insert into book(id, title, isbn_13, isbn_10)
--values(3,'Essex County', '978-1-60309-038-4', '1-603-09038-X');
--insert into book(id, title, isbn_13, isbn_10)
--values(4,'Hey, Mister (Vol 1)', '978-1-891830-02-0', '1-891-83002-3');
--insert into book(id, title, isbn_13, isbn_10)
--values(5,'The Underwater Welder', '978-1-60309-398-9', '1-603-09398-2');

insert into book(id, title)
values(1,'American Elf');
insert into book(id, title)
values(2,'Cosmoknights');
insert into book(id, title)
values(3,'Essex County');
insert into book(id, title)
values(4,'Hey, Mister (Vol 1)');
insert into book(id, title)
values(5,'The Underwater Welder');


insert into book_author(book_id, author_id)
values(1, 1);
insert into book_author(book_id, author_id)
values(1, 2);
insert into book_author(book_id, author_id)
values(1, 3);
insert into book_author(book_id, author_id)
values(2, 4);
insert into book_author(book_id, author_id)
values(3, 4);
insert into book_author(book_id, author_id)
values(4, 2);
insert into book_author(book_id, author_id)
values(4, 5);
insert into book_author(book_id, author_id)
values(4, 6);
insert into book_author(book_id, author_id)
values(5, 7);

insert into publisher(id, name)
values(1, 'Paste Magazine');
insert into publisher(id, name)
values(2, 'Publishers Weekly');
insert into publisher(id, name)
values(3, 'Graywolf Press');
insert into publisher(id, name)
values(4, 'McSweeney''s');

insert into book_dtls(book_id, publisher_id, price, edition, publication_year, isbn_13, isbn_10)
values(1, 1,1000, 'Book 2', 2004, '978-1-891830-85-3', '1-891-83085-6');
insert into book_dtls(book_id, publisher_id, price, edition, publication_year, isbn_13, isbn_10)
values(2, 2, 2000, 'Book 1', 2019, '978-1-60309-454-2', '1-603-09454-7');
insert into book_dtls(book_id, publisher_id, price, edition, publication_year, isbn_13, isbn_10)
values (3, 3, 500, '', 1990, '978-1-60309-038-4', '1-603-09038-X');
insert into book_dtls(book_id, publisher_id, price, edition, publication_year, isbn_13, isbn_10)
values (4, 3, 1200, 'After School Special', 2000, '978-1-891830-02-0', '1-891-83002-3');
insert into book_dtls(book_id, publisher_id, price, edition, publication_year, isbn_13, isbn_10)
values(5, 4, 3000, '', 2022, '978-1-60309-398-9', '1-603-09398-2');