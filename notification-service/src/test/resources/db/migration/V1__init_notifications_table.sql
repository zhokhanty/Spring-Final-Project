CREATE TABLE notifications (
                               id BIGSERIAL PRIMARY KEY,

                               user_id BIGINT NOT NULL,

                               type VARCHAR(50) NOT NULL,

                               status VARCHAR(30) NOT NULL,

                               payload TEXT,

                               source_event_id VARCHAR(255) NOT NULL,

                               created_at TIMESTAMP NOT NULL,

                               read_at TIMESTAMP,

                               CONSTRAINT uk_notification_source_event
                                   UNIQUE (source_event_id)
);
