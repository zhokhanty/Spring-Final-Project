CREATE TABLE IF NOT EXISTS book_history (
                              id BIGSERIAL PRIMARY KEY,
                              book_id BIGINT NOT NULL,
                              from_user VARCHAR(255),
                              to_user VARCHAR(255),
                              given_at TIMESTAMP,
                              returned_at TIMESTAMP,

                              status VARCHAR(50),

                              CONSTRAINT fk_book_history_book
                                  FOREIGN KEY (book_id)
                                      REFERENCES books (id)
                                      ON DELETE CASCADE
);
