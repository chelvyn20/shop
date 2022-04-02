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