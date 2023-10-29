# ATM Interface

![Java](https://img.shields.io/badge/Java-11-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.5-green)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)

A Java Spring Boot application that simulates ATM functionalities, providing users with the ability to perform various banking operations. This application includes features like transactions history, withdrawals, deposits, transfers, and quitting.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)

## Features

- User authentication through user ID and PIN.
- View user details, including full name, account number, and account balance.
- Update user information (full name and PIN).
- Perform withdrawals from the account.
- Make deposits to the account.
- Transfer funds between accounts.
- View transaction history.

## Technologies

- Java
- Spring Boot
- MySQL (for database)
- Maven (for build and dependency management)

## Getting Started

1. **Prerequisites**: Ensure you have Java and Maven installed on your system.

2. **Database Configuration**: Set up a MySQL database and configure the database connection properties in the `application.properties` file.

3. **Build and Run**: Build the application using Maven and run it.

```bash
mvn clean install
mvn spring-boot:run
The application will be accessible at http://localhost:8080.

Usage

To create a new user account, use the /user/signup endpoint by providing the user's full name, PIN, and initial balance.
To login, use /user/login endpoint by providing the user id and user pin
/user/details/{userId} to get user details.
/user/update/{userId} to update user information.
/user/history/{userId} to get user transaction history.

To perform transactions, you can use the following endpoints:
/transaction/withdraw to perform a withdrawal.
/transaction/deposit to make a deposit.
/transaction/transfer to initiate a fund transfer.
/transaction/details/{transactionId} to view transaction details.


Contributing
Contributions are welcome! Feel free to submit issues and pull requests.
