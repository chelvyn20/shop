-- ms_customer
DROP TABLE IF EXISTS ms_customer;

CREATE TABLE ms_customer (
	id VARCHAR(9) PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
    call_number VARCHAR(15) NOT NULL,
	created_time TIMESTAMP NOT NULL,
	created_by INT4 NOT NULL,
	updated_time TIMESTAMP NULL,
	updated_by INT4 NULL,
	deleted_time TIMESTAMP NULL,
	deleted_by INT4 NULL,
	rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
	OIDS=FALSE
);

SELECT * FROM ms_customer;

-- ms_category
DROP TABLE IF EXISTS ms_category;

CREATE TABLE ms_category (
	id VARCHAR(6) PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
	created_time TIMESTAMP NOT NULL,
	created_by INT4 NOT NULL,
	updated_time TIMESTAMP NULL,
	updated_by INT4 NULL,
	deleted_time TIMESTAMP NULL,
	deleted_by int4 NULL,
	rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
	OIDS=FALSE
);

SELECT * FROM ms_category;


-- product_category
DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category (
    product_id VARCHAR(6) NOT NULL,
    category_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (product_id, category_id)
);

SELECT * FROM product_category;


-- ms_product
DROP TABLE IF EXISTS ms_product;

CREATE TABLE ms_product (
	id VARCHAR(6) PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
	stock INT4 NULL,
	created_time TIMESTAMP NOT NULL,
	created_by INT4 NOT NULL,
	updated_time TIMESTAMP NULL,
	updated_by INT4 NULL,
	deleted_time TIMESTAMP NULL,
	deleted_by int4 NULL,
	rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
	OIDS=FALSE
);

SELECT * FROM ms_product;


-- tx_sale
DROP TABLE IF EXISTS tx_sale;

CREATE TABLE tx_sale (
	id VARCHAR(12) PRIMARY KEY NOT NULL,
    total_price NUMERIC(15, 2) NOT NULL,
	total_quantity INT4 NOT NULL,
    customer_id VARCHAR(9) NOT NULL,
    created_time TIMESTAMP NOT NULL,
    created_by INT4 NOT NULL
)
WITH (
	OIDS=FALSE
);

SELECT * FROM tx_sale;


-- sale-product
DROP TABLE IF EXISTS sale_product;

CREATE TABLE sale_product (
    sale_id VARCHAR(12) NULL,
    product_id VARCHAR(6) NOT NULL,
    category_id VARCHAR(6) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
	quantity INT4 NOT NULL,
	PRIMARY KEY (sale_id, product_id, category_id)
)
WITH (
	OIDS=FALSE
);

SELECT * FROM sale_product;

DROP TABLE IF EXISTS ms_role;

CREATE TABLE ms_role (
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
	created_time TIMESTAMP NOT NULL DEFAULT NOW()::TIMESTAMP,
	created_by INT4 NOT NULL DEFAULT 1::INT4,
	updated_time TIMESTAMP NULL,
	updated_by INT4 NULL,
	deleted_time TIMESTAMP NULL,
	deleted_by INT4 NULL,
	rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
	OIDS=FALSE
);

INSERT INTO ms_role (id, name, rec_status) VALUES
(1, 'ROLE_ADMIN', 'A'),
(2, 'ROLE_USER', 'A');

SELECT * FROM ms_role;

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role (
    user_id INT4 NOT NULL,
    role_id INT4 NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO user_role(user_id, role_id)
VALUES (1,1), (2,2), (3,2);

SELECT * FROM user_role;

DROP TABLE IF EXISTS ms_user;

CREATE TABLE ms_user (
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	created_time TIMESTAMP NOT NULL DEFAULT NOW()::TIMESTAMP,
	created_by INT4 NOT NULL DEFAULT 1::INT4,
	updated_time TIMESTAMP NULL,
	updated_by INT4 NULL,
	deleted_time TIMESTAMP NULL,
	deleted_by INT4 NULL,
	rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
)
WITH (
	OIDS=FALSE
);

INSERT INTO ms_user(id, name, username, password, rec_status) VALUES
(1, 'Admin', 'admin@gmail.com', '$2a$10$f.Bb7fdjFhutOSl2./pgLeGvLtq6hjhkIAMr11eSuMMO62Brr8L5S', 'A'),
(2, 'Siva', 'siva@gmail.com', '$2a$10$f.Bb7fdjFhutOSl2./pgLeGvLtq6hjhkIAMr11eSuMMO62Brr8L5S', 'A'),
(3, 'DemoUser', 'user@gmail.com', '$2a$10$f.Bb7fdjFhutOSl2./pgLeGvLtq6hjhkIAMr11eSuMMO62Brr8L5S', 'A');

SELECT * FROM ms_user;



SELECT * FROM sale_product;
SELECT * FROM tx_sale;
SELECT * FROM ms_product;
SELECT * FROM product_category;
SELECT * FROM ms_category;
SELECT * FROM ms_customer;

