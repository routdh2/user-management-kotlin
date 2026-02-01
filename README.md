# User Management Service

A **User Management REST API** built using **Kotlin + Spring Boot** with **MySQL** as the database.  
The application supports basic CRUD operations for managing users.

---

## 📌 Features

- Create a user
- Update a user
- Get user by ID
- Get all users
- Delete a user
- Input validation using Jakarta Bean Validation
- Clean layered architecture (Controller, Service, Repository)
- MySQL database (Docker-based)

---

## 🛠 Tech Stack

- Kotlin
- Spring Boot
- Spring Data JPA
- MySQL
- Docker
- Gradle

---

## 📂 Project Structure

```javascript
com.example.usermanagement
 ├── controller   → REST APIs
 ├── service      → Business logic
 ├── repository   → Data access layer
 ├── model        → JPA entities
 ├── dto          → Request / Response DTOs
 └── exception    → Global exception handling

```

---

## ⚙️ Prerequisites

Make sure you have the following installed:

- Java 17+
- IntelliJ IDEA (recommended)
- Docker & Docker Desktop
- Git

---

## 🚀 How to Run the Application (Step-by-Step)

### Step 1: Clone the repository

```bash
git clone https://github.com/routdh2/user-management-kotlin.git
cd user-management-kotlin
```
### Step 2: Start MySQL using Docker

Run the following command:

```bash
docker run --name user-mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=user_management_db \
  -p 3306:3306 \
  -d mysql:8.0
```
### Step 3: Build the project & run the application
```bash
./mvnw clean install 
./mvnw spring-boot:run
```
## API Reference

#### Create User

```http
  POST /users
```

| Request Body        | Type   | Description  |
|:--------------------|:-------| :----------- |
| `CreateUserRequest` | `User` | **Required** |

```bash
{
  "name": "John Doe",
  "email": "john@example.com"
}
```
#### Get User By ID

```http
  GET /users/{id}
```

| Parameter | Type   | Description   |
| :-------- |:-------| :------------ |
| `id`      | `Long` | **Required**. |

#### Get All Users

```http
  GET /users
```

#### Update User

```http
  PUT /users/{id}
```
```bash
{
  "name": "John Updated",
  "email": "john.updated@example.com"
}
```

#### Delete User

```http
  DELETE /users/{id}
```