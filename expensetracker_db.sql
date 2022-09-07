drop database expensetrackerdb;
drop user expensetracker;
create user expensetracker with password 'password';
create database expensetrackerdb with template=template0 owner=expensetracker;
\connect expensetrackerdb;
alter default privileges grant all on tables to expensetracker;
alter default privileges grant all on sequences to expensetracker;

create table et_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table et_categories(
category_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
description varchar(50) not null
);
alter table et_categories add constraint cat_users_fk
foreign key (user_id) references et_users(user_id);

create table et_transactions(
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric(10,2) not null,
note varchar(50) not null,
transaction_date bigint not null
);
alter table et_transactions add constraint trans_cat_fk
foreign key (category_id) references et_categories(category_id);
alter table et_transactions add constraint trans_users_fk
foreign key (user_id) references et_users(user_id);

create sequence et_users_seq increment 1 start 1;
create sequence et_categories_seq increment 1 start 1;
create sequence et_transactions_seq increment 1 start 1000;

-------
insert into user_account (user_id, email, first_name, last_name, password) values (4, 'a@gmail.com', 'a', 'a_lastname', 'test123');
insert into user_account (user_id, email, first_name, last_name, password) values (5, 'b@gmail.com', 'b', 'b_lastname', 'test123');
insert into user_account (user_id, email, first_name, last_name, password) values (6, 'c@gmail.com', 'c', 'c_lastname', 'test123');
insert into user_account (user_id, email, first_name, last_name, password) values (7, 'd@gmail.com', 'd', 'd_lastname', 'test123');
insert into user_account (user_id, email, first_name, last_name, password) values (8, 'e@gmail.com', 'e', 'e_lastname', 'test123');
insert into user_account (user_id, email, first_name, last_name, password) values (9, 'f@gmail.com', 'f', 'f_lastname', 'test123');
insert into user_account (user_id, email, first_name, last_name, password) values (10, 'g@gmail.com', 'g', 'g_lastname', 'test123');