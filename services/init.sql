CREATE DATABASE customer;
CREATE DATABASE account;

\c customer

CREATE type gender AS enum (
  'MALE',
  'FEMALE',
  'DIVERSE'
);
CREATE CAST (varchar AS gender) WITH INOUT AS IMPLICIT;

CREATE TABLE person (
  id             SERIAL NOT NULL, 
  name           varchar(100) NOT NULL, 
  gender	     gender NOT NULL,
  age            integer NOT NULL, 
  identification varchar(13) NOT NULL UNIQUE, 
  address        varchar(100) NOT NULL, 
  phone          varchar(10) NOT NULL, 
  PRIMARY KEY (id));
  
CREATE TABLE customer (
  id       integer NOT NULL, 
  password varchar(255) NOT NULL, 
  active   boolean NOT NULL, 
  PRIMARY KEY (id));
  
ALTER TABLE customer ADD CONSTRAINT FKclient546991 FOREIGN KEY (id) REFERENCES person (id);

\c account

create type account_type as enum(
	'SAVING',
	'CHECKING'
);

CREATE CAST (varchar AS account_type) WITH INOUT AS IMPLICIT;

create type movement_type as enum(
	'DEBIT',
	'CREDIT'
);

CREATE CAST (varchar AS movement_type) WITH INOUT AS IMPLICIT;

CREATE TABLE account (
  id              SERIAL NOT NULL, 
  account_number  varchar(10) NOT NULL UNIQUE, 
  account_type    account_type NOT NULL, 
  initial_balance numeric(10, 2) NOT NULL, 
  active          boolean NOT NULL, 
  customer_id     integer NOT NULL, 
  PRIMARY KEY (id));  
  
CREATE TABLE movement (
  id             SERIAL NOT NULL,
  amount         numeric(10, 2) NOT NULL,
  created_date   date NOT NULL,
  before_balance numeric(10,2) not null,
  after_balance  numeric(10,2) not null,
  movement_type  movement_type NOT NULL,
  account_id     integer NOT NULL,
  PRIMARY KEY (id));
  
ALTER TABLE movement ADD CONSTRAINT FKmovement693098 FOREIGN KEY (account_id) REFERENCES account (id);