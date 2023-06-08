-- schema.sql

-- Employee table
CREATE TABLE IF NOT EXISTS employee(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email_id VARCHAR(100) NOT NULL
);