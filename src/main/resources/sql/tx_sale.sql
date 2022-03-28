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

