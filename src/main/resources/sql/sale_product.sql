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

