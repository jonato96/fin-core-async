CREATE DATABASE client;
CREATE DATABASE account;

\c client

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
  
CREATE TABLE client (
  id       integer NOT NULL, 
  password varchar(50) NOT NULL, 
  status   char(1) NOT NULL, 
  PRIMARY KEY (id));
  
ALTER TABLE client ADD CONSTRAINT FKclient546991 FOREIGN KEY (id) REFERENCES person (id);

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
  status          char(1) NOT NULL, 
  client_id       integer NOT NULL, 
  PRIMARY KEY (id));  
  
CREATE TABLE movements (
  id            SERIAL NOT NULL, 
  amount        numeric(10, 2) NOT NULL, 
  movement_date date NOT NULL, 
  balance       numeric(10, 2) NOT NULL, 
  movement_type movement_type NOT NULL, 
  account_id    integer NOT NULL, 
  PRIMARY KEY (id));
  
ALTER TABLE movements ADD CONSTRAINT FKmovements693098 FOREIGN KEY (account_id) REFERENCES account (id);