--liquibase formatted sql

--changeset mponcyli:1
create table Category (
    id int primary key,
    name varchar(255),
    description text
);

create table Cups(
    id int primary key,
    name varchar(255),
    category_id int,
    description text,
    created_date timestamp,
    modificated_date timestamp
);

--changeset mponcyli:2 #runOnChange
drop table Category;
drop table Cups;

create table category (
    id int primary key,
    name varchar(255),
    description text
);

create table cups(
    id int primary key,
    name varchar(255),
    category_id int,
    description text,
    created_date timestamp,
    modificated_date timestamp
);

--changeset mponcyli:3
drop table category;

create table categories (
    id int primary key,
    name varchar(255),
    description text
);

--changeset mponcyli:4

ALTER TABLE cups RENAME created_date TO create_date;
ALTER TABLE cups RENAME modificated_date TO modification_date;

--changeset mponcyli:5
drop table categories;
drop table cups;

create table categories (
    id serial,
    name varchar(255),
    description text
);

create table cups(
    id serial,
    name varchar(255),
    category_id int,
    description text,
    create_date timestamp,
    modification_date timestamp
);

--changeset mponcyli:6
drop table categories;
drop table cups;

create table categories (
    id serial not null,
    name varchar(255),
    description text,
    primary key(id)
);

create table cups(
    id serial not null,
    name varchar(255),
    category_id int,
    description text,
    create_date timestamp,
    modification_date timestamp,
    primary key(id)
);

--changeset mponcyli:7
alter table cups add column status boolean;