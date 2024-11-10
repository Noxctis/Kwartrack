CREATE DATABASE kwartrack;
USE kwartrack;

-- Table to store user information with password encryption standards
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash CHAR(64) NOT NULL,  -- storing a SHA-256 hash
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for account information (for tracking balances and types)
CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    account_name VARCHAR(50) NOT NULL,
    account_type ENUM('cash', 'bank', 'credit', 'utang') NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Table for transactions (tracking income and expenses)
CREATE TABLE Transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT, -- The user making the transaction
    amount DECIMAL(10, 2),
    transaction_type ENUM('income', 'expense', 'debt_payment') NOT NULL,
    related_user_id INT, -- The related user for transactions (e.g., creditor)
    date DATE NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (related_user_id) REFERENCES User(user_id)
);


-- Table to track 'utang' (debts)
CREATE TABLE Debt (
    debt_id INT PRIMARY KEY AUTO_INCREMENT,
    debtor_user_id INT, -- User who owes the debt
    creditor_user_id INT, -- User to whom the debt is owed
    amount DECIMAL(10, 2),
    due_date DATE,
    status ENUM('unpaid', 'paid') DEFAULT 'unpaid',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (debtor_user_id) REFERENCES User(user_id),
    FOREIGN KEY (creditor_user_id) REFERENCES User(user_id)
);

