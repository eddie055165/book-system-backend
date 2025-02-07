CREATE TABLE IF NOT EXISTS user (
                                    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    phone_number VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(100),
    registration_time TIMESTAMP,
    last_login_time TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS book (
                                    isbn VARCHAR(20) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    introduction TEXT
    );

CREATE TABLE IF NOT EXISTS inventory (
                                         inventory_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         isbn VARCHAR(20) NOT NULL,
    store_time TIMESTAMP,
    status ENUM('AVAILABLE', 'BORROWED', 'UNDER_MAINTENANCE', 'LOST', 'DAMAGED', 'DISCARDED') NOT NULL,
    FOREIGN KEY (isbn) REFERENCES book(isbn)
    );

CREATE TABLE IF NOT EXISTS borrowing_record (
                                                user_id BIGINT NOT NULL,
                                                inventory_id BIGINT NOT NULL,
                                                borrowing_time TIMESTAMP,
                                                return_time TIMESTAMP,
                                                PRIMARY KEY (user_id, inventory_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id)
    );