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



SELECT u.*, r.id, r.name
FROM
    ms_user AS u
    JOIN user_role AS ur ON ur.user_id = u.id
    JOIN ms_role AS r ON r.id = ur.role_id
WHERE rec_status = ? AND UPPER(username) = UPPER(:username);