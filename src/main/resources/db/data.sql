INSERT INTO user (phone_number, password, user_name, registration_time, last_login_time)
VALUES
    ('1234567890', 'password1', 'John Doe', '2023-01-01 10:00:00', '2023-01-10 12:00:00'),
    ('0987654321', 'password2', 'Jane Smith', '2023-01-05 15:00:00', '2023-01-15 16:00:00');

INSERT INTO book (isbn, name, author, introduction)
VALUES
    ('978-3-16-148410-0', 'Book One', 'Author One', 'Introduction to Book One'),
    ('978-1-234-56789-7', 'Book Two', 'Author Two', 'Introduction to Book Two');

INSERT INTO inventory (isbn, store_time, status)
VALUES
    ('978-3-16-148410-0', '2023-01-01 10:00:00', 'AVAILABLE'),
    ('978-1-234-56789-7', '2023-01-05 15:00:00', 'AVAILABLE');

INSERT INTO borrowing_record (user_id, inventory_id, borrowing_time, return_time)
VALUES
    (1, 1, '2023-01-10 12:00:00', '2023-01-20 12:00:00'),
    (2, 2, '2023-01-15 16:00:00', NULL);