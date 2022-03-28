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