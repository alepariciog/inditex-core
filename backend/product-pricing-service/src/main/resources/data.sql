CREATE TABLE prices (
    id UUID PRIMARY KEY,
    brand_id BIGINT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    price_list BIGINT,
    product_id BIGINT,
    priority INT,
    price DECIMAL(10, 2),
    curr VARCHAR(3)
);

INSERT INTO PRICES (id, price_list, brand_id, start_date, end_date, product_id, priority, price, curr) VALUES ('a5042602-c31d-457f-a7f9-2aeb4a78cbbd'::uuid, 1, 1, TIMESTAMP '2020-06-14 00:00:00', TIMESTAMP '2020-12-31 23:59:59', 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (id, price_list, brand_id, start_date, end_date, product_id, priority, price, curr) VALUES ('36f425bd-f486-4615-8e9c-e21e1a779c5d'::uuid, 2, 1, TIMESTAMP '2020-06-14 15:00:00', TIMESTAMP '2020-06-14 18:30:00', 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (id, price_list, brand_id, start_date, end_date, product_id, priority, price, curr) VALUES ('48106b26-75bd-4774-8525-e3fd76761834'::uuid, 3, 1, TIMESTAMP '2020-06-15 00:00:00', TIMESTAMP '2020-06-15 11:00:00', 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (id, price_list, brand_id, start_date, end_date, product_id, priority, price, curr) VALUES ('a9993d50-2381-4ed3-a10e-b4fde27826ae'::uuid, 4, 1, TIMESTAMP '2020-06-15 16:00:00', TIMESTAMP '2020-12-31 23:59:59', 35455, 1, 38.95, 'EUR');
