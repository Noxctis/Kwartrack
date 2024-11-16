USE kwartrack;
SHOW TABLES;
DESCRIBE utang;
SELECT * FROM users;

SELECT * FROM users;
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
