DROP TABLE IF EXISTS product_sale;

CREATE TABLE product_sale (
    product_id VARCHAR(6) NOT NULL,
    sale_id VARCHAR(12) NOT NULL,
    PRIMARY KEY (product_id, sale_id)
)
WITH (
	OIDS=FALSE
);

SELECT * FROM product_sale;

