CREATE TABLE IF NOT EXISTS configuration (
    id SERIAL PRIMARY KEY,
    customer VARCHAR(255) NOT NULL UNIQUE,
    configuration TEXT NOT NULL
);