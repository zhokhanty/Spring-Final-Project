CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(255),
                       full_name VARCHAR(255),
                       status VARCHAR(50),
                       version BIGINT
);