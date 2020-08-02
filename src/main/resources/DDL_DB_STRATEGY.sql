create user appuser with password 'appuserpassword';

CREATE TABLE if not exists public.DATASOURCECONFIG (
   id bigint PRIMARY KEY,
   driverclassname VARCHAR(255),
   url VARCHAR(255),
   name VARCHAR(255),
   username VARCHAR(255),
   password VARCHAR(255),
   initialize BOOLEAN
);

INSERT INTO DATASOURCECONFIG VALUES (1, 'org.postgresql.Driver', 'jdbc:postgresql://localhost:5432/tenant1?ApplicationName=MultiTenant', 'tenant1', 'appuser', 'appuserpassword', true);
INSERT INTO DATASOURCECONFIG VALUES (2, 'org.postgresql.Driver', 'jdbc:postgresql://localhost:5432/tenant2?ApplicationName=MultiTenant', 'tenant2', 'appuser', 'appuserpassword', true);

create database tenant1;
\c tenant1
create table employee(id bigint, name varchar(200));
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO appuser;

create database tenant2;
\c tenant2
create table employee(id bigint, name varchar(200));
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO appuser;

