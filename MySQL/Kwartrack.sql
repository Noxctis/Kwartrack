-- Create a database
CREATE DATABASE IF NOT EXISTS kwartrack;
USE kwartrack;

-- Users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- User sessions table
CREATE TABLE user_sessions (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    session_token VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Categories table (for expenses, income, etc.)
CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Insert default categories
INSERT INTO categories (name) VALUES
('Groceries'),
('Transport'),
('Leisure'),
('Restaurant'),
('Health'),
('Family'),
('Pets'),
('Education'),
('Traveling'),
('Gifts'),
('Shopping');

-- Income table
CREATE TABLE incomes (
    income_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    source VARCHAR(255) NOT NULL,  -- User-defined source
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Expenses table
CREATE TABLE expenses (
    expense_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL
);

-- Debts table
CREATE TABLE debts (
    debt_id INT AUTO_INCREMENT PRIMARY KEY,
    debtor_id INT NOT NULL,  -- The user who owes the debt
    creditor_id INT NOT NULL,  -- The user to whom the debt is owed
    amount DECIMAL(10, 2) NOT NULL,
    date_issued DATE NOT NULL,  -- Use CURRENT_DATE
    date_due DATE,
    is_paid BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (debtor_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (creditor_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Transactions table
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    type ENUM('income', 'expense', 'debt') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    category_id INT,
    related_id INT, -- References debt_id, income_id, or expense_id as applicable
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL
);

-- Trigger to update transactions after inserting into incomes
DELIMITER //
CREATE TRIGGER after_income_insert
AFTER INSERT ON incomes
FOR EACH ROW
BEGIN
    -- Insert corresponding transaction for the income
    INSERT INTO transactions (user_id, type, amount, date, source, related_id)
    VALUES (NEW.user_id, 'income', NEW.amount, NEW.date, NEW.source, NEW.income_id);
END;
//
DELIMITER ;

-- Trigger to update transactions after inserting into expenses
DELIMITER //
CREATE TRIGGER after_expense_insert
AFTER INSERT ON expenses
FOR EACH ROW
BEGIN
    -- Insert a transaction record for the expense
    INSERT INTO transactions (user_id, type, amount, date, category_id, related_id)
    VALUES (NEW.user_id, 'expense', NEW.amount, NEW.date, NEW.category_id, NEW.expense_id);
END;
//
DELIMITER ;

-- Trigger to update transactions after inserting into debts
-- Trigger to update transactions after inserting into incomes
DELIMITER //
CREATE TRIGGER after_income_insert
AFTER INSERT ON incomes
FOR EACH ROW
BEGIN
    -- Insert corresponding transaction for the income
    INSERT INTO transactions (user_id, type, amount, date, related_id)
    VALUES (NEW.user_id, 'income', NEW.amount, NEW.date, NEW.income_id);
END;
//
DELIMITER ;

-- Trigger to update transactions after deleting from debts
DELIMITER //
CREATE TRIGGER after_debt_delete
AFTER DELETE ON debts
FOR EACH ROW
BEGIN
    UPDATE transactions
    SET related_id = NULL
    WHERE related_id = OLD.debt_id AND type = 'debt';
END;
//
DELIMITER ;

-- Trigger to update transactions after deleting from incomes
DELIMITER //
CREATE TRIGGER after_income_delete
AFTER DELETE ON incomes
FOR EACH ROW
BEGIN
    UPDATE transactions
    SET related_id = NULL
    WHERE related_id = OLD.income_id AND type = 'income';
END;
//
DELIMITER ;

-- Trigger to update transactions after deleting from expenses
DELIMITER //
CREATE TRIGGER after_expense_delete
AFTER DELETE ON expenses
FOR EACH ROW
BEGIN
    UPDATE transactions
    SET related_id = NULL
    WHERE related_id = OLD.expense_id AND type = 'expense';
END;
//
DELIMITER ;

