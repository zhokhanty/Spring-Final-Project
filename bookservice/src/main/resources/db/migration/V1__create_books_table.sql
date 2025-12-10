
CREATE TABLE IF NOT EXISTS books (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       year INT NOT NULL,
                       status VARCHAR(20) DEFAULT 'AVAILABLE'
);
