DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role (
    user_id INT4 NOT NULL,
    role_id INT4 NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO user_role(user_id, role_id)
VALUES (1,1), (2,2), (3,2);

SELECT * FROM user_role;