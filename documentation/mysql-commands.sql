-- create database 
create database covid;

-- select database
use covid;

-- list all tables
show tables;

-- create table 
create table users(
  username varchar(50) not null primary key,
  password varchar(50) not null,
  enabled boolean not null);

create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  
-- add constraint
create unique index ix_auth_username on authorities (username,authority);

-- see constraint
select column_name, constraint_name, referenced_column_name, referenced_table_name
from information_schema.key_column_usage
where table_name = 'users';

-- insert 
insert into users (username, password, enabled) 
values ('sachin','abc',true);

insert into users (username, password, enabled) 
values ('ganesh','abc',true);

insert into authorities(username, authority)
values ('sunil','USER');

insert into authorities(username, authority)
values ('andrea','ADMIN');