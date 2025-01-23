# Introduction
Kwartrack is a money-tracking application designed to address the growing need for financial literacy and management, particularly among Filipinos. The application empowers users by providing tools to track income, expenses, purchases, and small debts ("utang"), which are commonly overlooked in daily financial practices. With a focus on accessibility and ease of use, Kwartrack caters to students, small businesses, and individuals striving to manage their finances effectively.
The application not only simplifies financial tracking but also provides personalized insights, budgeting tools, and a "Financial Health Score" to encourage better financial habits. By integrating advanced features such as machine learning and gamification, Kwartrack goes beyond basic tracking to offer recommendations and incentives that align with users' financial goals. This project showcases how software development principles can solve real-world problems while fostering financial responsibility.

# Object Oriented Analysis
The app provides a Financial Health Score, personalized insights, and secure data storage. Key Features Include: 
Functional Features:
- Money Tracking: The app allows users to track income, expenses, and debts. This includes adding, editing, and categorizing transactions.
- Financial Health Score: The app calculates a score based on user spending habits, debt management, and savings patterns.
- Registration and Login: Secure user registration and authentication to protect user data.
- Personalized Insights: The app provides tailored tips and suggestions based on the user's financial behavior.
- Secure Data Storage: Data is stored securely using encryption (e.g., hashing) to ensure user privacy.
- Database Integration: A database is used to store and retrieve user data, transaction history, and other relevant information.

Non-Functional Features:
-- User-Friendliness: The app should be intuitive and easy to use, even for users with limited technical skills.
-- Responsiveness: The app should have high performance and low latency to provide a smooth user experience.
-- Scalability: The app's design should allow for future expansion of features and accommodate a growing user base.
-- Security: User data and privacy should be protected through robust security measures.

Classes:
-- User: Handles user details, authentication, and preferences. It is the central entity that interacts with other components like transactions, budgets, debts and financial health scoring.
-- Transaction: Manages income, expenses, and debt records with relevant attributes such as amount, category, and date.
-- Debt: Tracks and manages the user’s outstanding financial obligations.
-- FinancialHealthScore: Calculates and updates the user’s financial health and stability based on their data.
-- Budget: Tracks user-defined budgets and alerts for overspending.

Relationships:
-- User ↔ Transactions:
Each user is associated with multiple Transaction objects, representing their income, balance, expenses and debts.
-- User ↔ Debt:
Each user is associated with multiple Debt objects, each tracking a specific financial obligation.
-- Debt ↔ FinancialHealthScore:
The FinancialHealthScore incorporates debt data to evaluate the user’s debt-to-income ratio and financial stability.
-- Transactions ↔ FinancialHealthScore:
The FinancialHealthScore class processes data from income, balance, expense, transaction and debt objects to calculate the user's financial health score, considering factors like spending habits and debts.

This object-oriented approach ensures that Kwartrack is flexible and future-proof. By defining distinct classes and clear relationships, the system can easily adapt to changing user needs and integrate new features or technologies without disrupting existing functionality.


# Object Oriented Design
The program implements the four object oriented design principles - Abstraction, Encapsulation, Inheritance and Polymorphism - through the use of classes and objects.
-- Abstraction is applied through the class FinancialHealthScore, calculating the user’s financial score by interacting with Transaction, Debt and Budget data, abstracting the calculation details and exposing only the financial score and insights.
-- Encapsulation is applied in all classes like User, Transaction, Debt, Budget, Expense, Balance and Financial HealthScore by using private variables and public getter and setter methods to access or modify them. This ensures that sensitive data like user details or debt balances are securely managed and cannot be accessed or altered directly.
-- Inheritance is applied where all the classes like User, Transaction, Debt, Budget, Expense, Balance and FinancialHealthScore extends to the customed javax.swing classes.
-- Polymorphism is applied in the system where classes like Transaction, Debt, Expense and Balance override common methods inherited from a base class or interface.

# Conclusion
Kwartrack exemplifies the effective application of object-oriented programming principles to solve a prevalent societal issue: financial mismanagement. By breaking down the system into modular, reusable, and extensible components, the application addresses the unique financial challenges faced by Filipinos, such as tracking small debts ("utang") and managing daily income and expenses.
Through its user-friendly design, personalized insights, and gamification features, Kwartrack not only provides a functional financial management tool but also promotes financial literacy and responsibility. The inclusion of secure data handling and machine learning ensures that the application remains both reliable and innovative.
Overall, the development of Kwartrack highlights the importance of combining technical expertise with real-world problem-solving to create impactful software solutions. This project demonstrates how object-oriented design can lead to scalable and maintainable systems that address critical needs, paving the way for continuous improvement and growth in the future.

