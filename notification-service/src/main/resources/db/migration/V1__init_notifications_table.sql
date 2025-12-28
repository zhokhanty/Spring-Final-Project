CREATE TABLE notifications (
                               id SERIAL PRIMARY KEY,
                               type VARCHAR(255),
                               message TEXT,
                               created_at TIMESTAMP DEFAULT NOW()
);
