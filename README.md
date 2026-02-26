# Banking App Demo

A robust RESTful API-based banking application built using **Java**, **Spring Boot**, and **MySQL**. This project demonstrates the core functionalities of a modern banking system, including account management, transactions, and data persistence.

---

## 🚀 Features

* **Account Management**: Create, update, and delete bank accounts.
* **Balance Inquiries**: Fetch account details and current balance in real-time.
* **Transactions**: 
    * **Deposit**: Safely add funds to an existing account.
    * **Withdrawal**: Remove funds with built-in validation for sufficient balance.
* **Global Exception Handling**: Custom error responses for scenarios like "Account Not Found" or "Insufficient Funds."
* **Data Persistence**: Relational data mapping using Spring Data JPA and MySQL.

---

## 🛠️ Tech Stack

* **Backend:** Java 17+, Spring Boot 3.x
* **Frameworks:** Spring Data JPA (Hibernate), Spring Web
* **Database:** MySQL
* **Build Tool:** Maven

---

## 📋 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/api/accounts` | Create a new bank account |
| **GET** | `/api/accounts/{id}` | Get details of a specific account |
| **GET** | `/api/accounts` | Get a list of all accounts |
| **PUT** | `/api/accounts/{id}/deposit` | Deposit money into an account |
| **PUT** | `/api/accounts/{id}/withdraw` | Withdraw money from an account |
| **DELETE** | `/api/accounts/{id}` | Close/Delete an account |

---
## 📂 Project Structure

```text
src/main/java/com/anish/banking
├── controller    # REST Endpoints
├── dto           # Data Transfer Objects
├── entity        # Database Models (JPA Entities)
├── exception     # Custom Exception Handling
├── mapper        # Logic to convert Entity to DTO and vice versa
├── repository    # Database Communication (JPA Repositories)
└── service       # Business Logic Layer

## ⚙️ Installation & Setup

### 1. Prerequisites
* **JDK 17** or higher installed.
* **MySQL Server** running locally.
* **Maven** (optional, you can use the included wrapper `./mvnw`).

### 2. Clone the Repository
```bash
git clone [https://github.com/AnishKoppisetty/BankingAppDemo.git](https://github.com/AnishKoppisetty/BankingAppDemo.git)
cd BankingAppDemo
### 3. Configure Database
Open src/main/resources/application.properties and update your MySQL credentials:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
Note: Ensure you have created a database named banking_db in MySQL before running.
