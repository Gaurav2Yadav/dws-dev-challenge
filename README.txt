This `README.md` provides a project overview, functionality details, setup instructions, API endpoints, potential improvements, and information on testing and dependencies.


DWS Dev Challenge

Project Overview
This project provides a simple REST service to manage accounts and perform money transfers between them. The application is built using Spring Boot, Gradle, and Lombok, and it includes functionalities to:
- Create a new account.
- Retrieve account details.
- Transfer money between accounts with validation to ensure that transfers are positive and no account ends up with a negative balance.

Functionality
- Account Management: The application allows users to create new accounts and retrieve account information by ID.
- Money Transfer: A secure transfer system where money can be transferred between two accounts. The transfer amount must always be positive, and no account can end up with a negative balance.
- Notification: A notification is sent to both account holders whenever a transfer occurs.

How to Run the Project
1. Clone the repository.
2. Build the project using Gradle:
   ---
   gradlew build
   ---
3. Run the application:
   ---
   gradlew bootRun
   ---
4. The API will be available at `http://localhost:8080/v1/accounts`.

API Endpoints
1. Create Account
   ---
   POST /v1/accounts
   ---
   Body Example:
   ---json
   {
     "id": "123",
     "name": "Gaurav Yadav",
     "balance": 1000
   }
   ---
2. **Retrieve Account**
   ---
   GET /v1/accounts/{accountId}
   ---

3. **Transfer Money**
   ---
   POST /v1/accounts/transfer
   ```
   Body Example:
   ---json
   {
     "accountFromId": "123",
     "accountToId": "456",
     "amount": 100
   }
   ---

Improvements for Production
Given more time, the following improvements would be made:
- Transaction History: Implement a feature to track and display the history of all transfers for each account.
- Enhanced Error Handling: Improve error handling with more detailed messages and custom exceptions, especially for edge cases like invalid account IDs or insufficient funds.
- Security Enhancements: Add authentication and authorization mechanisms (e.g., OAuth 2.0) to secure endpoints.
- Performance Optimization: Optimize the application for high concurrency, ensuring that the transfer logic scales efficiently under heavy loads.
- Logging and Monitoring: Implement centralized logging and monitoring to track application health and troubleshoot issues in a production environment.

 Testing
Unit tests are provided for key components, including account creation and money transfer. The `NotificationService` is mocked in the tests to isolate the logic for transfers.
To run the tests:
---
gradlew test
---

 Dependencies
- Spring Boot
- Gradle
- Lombok
- Validation API
---

This `README.md` provides a project overview, functionality details, setup instructions, API endpoints, potential improvements, and information on testing and dependencies. 