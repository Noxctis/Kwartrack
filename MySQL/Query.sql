USE kwartrack;
SHOW TABLES;
DESCRIBE debts;
SELECT * FROM users;

-- Insert user Janedoe
INSERT INTO users (firstname, lastname, username, email, password_hash)
VALUES ('Jane', 'Doe', 'janedoe', 'janedoe@example.com', 'hashed_password123');

-- Insert user Johndoe
INSERT INTO users (firstname, lastname, username, email, password_hash)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'hashed_password123');

-- Insert debt for Janedoe owing Johndoe, where is_paid = FALSE
INSERT INTO debts (debtor_id, creditor_id, amount, date_issued, date_due, is_paid)
VALUES (1, 2, 100.00, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY), FALSE);

-- Insert debt for Janedoe owing Johndoe, where is_paid = TRUE
INSERT INTO debts (debtor_id, creditor_id, amount, date_issued, date_due, is_paid)
VALUES (1, 2, 50.00, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY), TRUE);


#UPDATE debts
#SET is_paid = 1;

SELECT * FROM debts;

SELECT 
    d.debt_id,
    u1.username AS debtor,
    u2.username AS creditor,
    d.amount,
    d.date_issued,
    d.date_due,
    d.is_paid
FROM debts d
JOIN users u1 ON d.debtor_id = u1.user_id
JOIN users u2 ON d.creditor_id = u2.user_id
WHERE d.is_paid = FALSE;
SELECT VERSION();

#DELETE FROM users;