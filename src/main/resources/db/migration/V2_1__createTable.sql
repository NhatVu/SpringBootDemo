CREATE TABLE ${currentSchema}.category (
	category_id int4 NOT NULL,
	description varchar(255) NULL,
	title varchar(255) NULL,
	total_expense float8 NULL,
	user_id int4 NULL,
	CONSTRAINT category_pkey PRIMARY KEY (category_id)
);


CREATE TABLE ${currentSchema}."role" (
	id int8 NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);


CREATE TABLE ${currentSchema}.user_account (
	user_id int4 NOT NULL,
	email varchar(255) NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT user_account_pkey PRIMARY KEY (user_id),
	CONSTRAINT user_account_user_id_check CHECK ((user_id >= 3))
);


CREATE TABLE ${currentSchema}.user_account_roles (
	user_account_user_id int4 NOT NULL,
	roles_id int8 NOT NULL,
	CONSTRAINT fkgy9loc84s36fn6khqcpijrykc FOREIGN KEY (user_account_user_id) REFERENCES expensetracker.user_account(user_id),
	CONSTRAINT fkhgw1hs27q8latvqis3h4u9hh0 FOREIGN KEY (roles_id) REFERENCES expensetracker."role"(id)
);