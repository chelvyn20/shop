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

