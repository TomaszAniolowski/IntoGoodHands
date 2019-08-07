INSERT INTO institutions(description, name) VALUES ('Pomoc dzieciom z ubogich rodzin','Dbam o zdrowie');
INSERT INTO institutions(description, name) VALUES ('Pomoc w wybudzaniu dzieci ze śpiączki','A kogo');
INSERT INTO institutions(description, name) VALUES ('Pomoc osobom znajdującym się w trudnej sytuacji życiowej','Dla dzieci');
INSERT INTO institutions(description, name) VALUES ('Pomoc dla osób nieposiadających miejsca zamieszkania','Bez domu');

INSERT INTO categories(id,name) VALUES (1,'inne');
INSERT INTO categories(id,name) VALUES (2, 'ubrania, które nadają się do ponownego użycia');
INSERT INTO categories(id,name) VALUES (3, 'ubrania nie nadające się do ponownego użycia');
INSERT INTO categories(id,name) VALUES (4, 'atrybuty kuchenne');
INSERT INTO categories(id,name) VALUES (5, 'zabawki');
INSERT INTO categories(id,name) VALUES (6, 'książki');

INSERT INTO `charity-donation`.donations (id, city, phone_number, pick_up_comment, pick_up_date, pick_up_time, quantity, street, street_num, zip_code, institution_id) VALUES (1, 'Krosno', '505171299', '', '2019-08-23', '11:28:00', '2', 'Grzybowska', '93', '05-410', 2);

INSERT INTO `charity-donation`.donations_categories (donation_id, categories_id) VALUES (1, 2);