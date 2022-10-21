insert into rentalcars.roles (role_name) values('ROLE_ADMIN');
insert into rentalcars.roles (role_name) values('ROLE_USER');
insert into rentalcars.roles (role_name) values('ROLE_MODERATOR');

insert into rentalcars.users (user_name, surname, birth, login, password, email)
values('Admin',
       'Admin',
       '1993-05-29 14:30:25-07',
       'easyLogin',
       '$2a$10$uxOj/UoXpOlqYWWmV/IGTerEwQOZWVMMgCrfCr7akXAnLHoA5l.7a',
       'www.pixel93@mail.ru');

insert into rentalcars.l_role_user (user_id, role_id) values(1, 1);

insert into rentalcars.body_types (body_type_name) values ('седан');
insert into rentalcars.body_types (body_type_name) values ('универсал');
insert into rentalcars.body_types (body_type_name) values ('пикап');
insert into rentalcars.body_types (body_type_name) values ('хэтчбэк');
insert into rentalcars.body_types (body_type_name) values ('минивэн');
insert into rentalcars.body_types (body_type_name) values ('купе');
insert into rentalcars.body_types (body_type_name) values ('кабриолет');
insert into rentalcars.body_types (body_type_name) values ('кроссовер');
insert into rentalcars.body_types (body_type_name) values ('внедорожник');

insert into rentalcars.brands (brand_name) values ('Ford');
insert into rentalcars.brands (brand_name) values ('Mazda');
insert into rentalcars.brands (brand_name) values ('Opel');
insert into rentalcars.brands (brand_name) values ('BWM');
insert into rentalcars.brands (brand_name) values ('Peugeot');
insert into rentalcars.brands (brand_name) values ('Citroen');
insert into rentalcars.brands (brand_name) values ('Dodge');
insert into rentalcars.brands (brand_name) values ('Porsche');
insert into rentalcars.brands (brand_name) values ('Maserati');
insert into rentalcars.brands (brand_name) values ('Land Rover');
insert into rentalcars.brands (brand_name) values ('Pontiac');
insert into rentalcars.brands (brand_name) values ('Chevrolet');
insert into rentalcars.brands (brand_name) values ('Lincoln');
insert into rentalcars.brands (brand_name) values ('Audi');
insert into rentalcars.brands (brand_name) values ('Toyota');
insert into rentalcars.brands (brand_name) values ('Honda');
insert into rentalcars.brands (brand_name) values ('Geely');
insert into rentalcars.brands (brand_name) values ('Renault');
insert into rentalcars.brands (brand_name) values ('Lada');
insert into rentalcars.brands (brand_name) values ('Rover');;













