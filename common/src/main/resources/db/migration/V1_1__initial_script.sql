-- create schema rentalcars;
--
-- alter schema rentalcars owner to postgres;

create table if not exists users
(
	id bigserial not null
		constraint users_pk
			primary key,
	user_name varchar(20) default 'default name'::character varying not null,
	surname varchar(50) default 'default surname'::character varying not null,
	birth timestamp(6) not null,
	is_deleted boolean default false,
	creation_date timestamp(6) default CURRENT_TIMESTAMP(6),
	modification_date timestamp(6) default CURRENT_TIMESTAMP(6),
	login varchar(20) not null,
	password varchar(200) not null,
	email varchar(30) default 'default email'::character varying not null
);

alter table users owner to postgres;

create unique index if not exists users_id_uindex
	on users (id);

create unique index if not exists users_login_uindex
	on users (login);

create table if not exists roles
(
	id serial not null
		constraint roles_pk
			primary key,
	role_name varchar(20) default 'user'::character varying not null,
	is_deleted boolean default false,
	creation_date timestamp(6) default CURRENT_TIMESTAMP(6),
	modification_date timestamp default CURRENT_TIMESTAMP(6)
);

alter table roles owner to postgres;

create unique index if not exists roles_id_uindex
	on roles (id);

create table if not exists body_types
(
	id serial not null
		constraint body_types_pk
			primary key,
	body_type_name varchar(20) default 'default type'::character varying not null,
	is_available boolean default true
);

alter table body_types owner to postgres;

create unique index if not exists body_types_id_uindex
	on body_types (id);

create table if not exists driving_licenses
(
	id bigserial not null
		constraint driving_lisences_pk
			primary key,
	serial_number varchar(30) default 'default serial number'::character varying not null,
	date_of_issue timestamp(6) default CURRENT_TIMESTAMP(6),
	expiration_date timestamp(6) default CURRENT_TIMESTAMP(6),
	category varchar(15) default 'default category'::character varying,
	user_id integer not null
		constraint driving_lisences_users_id_fk
			references users
				on update cascade on delete cascade,
	is_deleted boolean default false,
	creation_date timestamp(6) default CURRENT_TIMESTAMP(6),
	modification_date timestamp(6) default CURRENT_TIMESTAMP(6)
);

alter table driving_licenses owner to postgres;

create sequence driving_lisence_id_seq;

alter sequence driving_lisence_id_seq owner to postgres;

create unique index if not exists driving_lisences_id_uindex
	on driving_licenses (id);

create unique index if not exists driving_lisences_id_user_uindex
	on driving_licenses (user_id);

create table if not exists brands
(
	id serial not null
		constraint brands_pk
			primary key,
	brand_name varchar(30) default 'default name'::character varying,
	is_available boolean default true
);

alter table brands owner to postgres;

create unique index if not exists brands_id_uindex
	on brands (id);

create table if not exists models
(
	id serial not null
		constraint models_pk
			primary key,
	model_name varchar(30) default 'default name'::character varying,
	brand_id integer not null
		constraint models_brands_id_fk
			references brands
				on update cascade on delete cascade,
	is_available boolean default true,
	creation_date timestamp(6) default CURRENT_TIMESTAMP(6),
	modification_date timestamp(6) default CURRENT_TIMESTAMP(6),
	body_type_id integer not null
		constraint models_body_types_id_fk
			references body_types
				on update cascade on delete cascade
);

alter table models owner to postgres;

create unique index if not exists models_id_uindex
	on models (id);

create table if not exists cars
(
	id bigserial not null
		constraint cars_pk
			primary key,
	model_id integer not null
		constraint cars_models_id_fk
			references models
				on update cascade on delete cascade,
	volume double precision,
	vin_number varchar(30) default 'default vin number'::character varying,
	is_available boolean default true,
	creation_date timestamp(6) default CURRENT_TIMESTAMP(6),
	modification_date timestamp(6) default CURRENT_TIMESTAMP(6),
	price double precision
);

alter table cars owner to postgres;

create unique index if not exists cars_id_uindex
	on cars (id);

create unique index if not exists cars_vin_number_uindex
	on cars (vin_number);

create table if not exists rental_agreements
(
	id bigserial not null
		constraint rental_agreements_pk
			primary key,
	user_id integer not null
		constraint rental_agreements_users_id_fk
			references users
				on update cascade on delete cascade,
	car_id bigint not null
		constraint rental_agreements_cars_id_fk
			references cars
				on update cascade on delete cascade,
	rental_start_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
	total_cost double precision,
	creation_date timestamp(6) default CURRENT_TIMESTAMP(6),
	modification_date timestamp(6) default CURRENT_TIMESTAMP(6),
	expiration_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table rental_agreements owner to postgres;

create unique index if not exists rental_agreements_id_uindex
	on rental_agreements (id);

create table if not exists l_role_user
(
	id bigserial not null
		constraint l_role_user_pk
			primary key,
	user_id bigint not null
		constraint l_role_user_users_id_fk
			references users
				on update cascade on delete cascade,
	role_id bigint not null
		constraint l_role_user_roles_id_fk
			references roles
				on update cascade on delete cascade
);

alter table l_role_user owner to postgres;

create unique index if not exists l_role_user_id_uindex
	on l_role_user (id);

create index if not exists l_role_user_user_id_role_id_index
	on l_role_user (user_id, role_id);

