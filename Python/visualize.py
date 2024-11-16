import mysql.connector
import pandas as pd
import matplotlib.pyplot as plt

# Connect to the MySQL database
conn = mysql.connector.connect(
    host='localhost',
    user='yourUsername',
    password='yourPassword',
    database='kwartrack'
)

# Query to fetch debt data
query = """
SELECT 
    u1.username AS debtor, 
    u2.username AS creditor, 
    d.amount, 
    d.status 
FROM 
    Debt d
JOIN 
    User u1 ON d.debtor_user_id = u1.user_id
JOIN 
    User u2 ON d.creditor_user_id = u2.user_id;
"""

# Load the data into a DataFrame
df = pd.read_sql(query, conn)

# Close the database connection
conn.close()

# Group data by debtor and sum amounts
debt_summary = df.groupby('debtor')['amount'].sum()

# Plot the data
plt.figure(figsize=(10, 6))
debt_summary.plot(kind='bar', color='skyblue')
plt.title('Total Debt Amount by Debtor')
plt.xlabel('Debtor')
plt.ylabel('Total Amount (PHP)')
plt.xticks(rotation=45)
plt.tight_layout()
plt.show()
