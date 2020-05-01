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

-- create table - questions
create table questions(
  question_id INT AUTO_INCREMENT PRIMARY KEY,
  question varchar(150) not null,
  control varchar(50) not null);

-- select questions
select * from questions;

-- insert question
insert into questions(question, control) values ('Name of your area?','input');
insert into questions(question, control) values ('which symtoms you have?','input');

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

