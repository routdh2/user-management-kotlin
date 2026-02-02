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
- Maven

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

## Impact analysis if we integrate with Kafka

Integrating Kafka into the User Management Service can have several impacts:
1. **Asynchronous Processing**: Kafka allows for asynchronous processing of user-related events (e.g., user creation, updates). This can improve the responsiveness of the API by offloading time-consuming tasks to background processes.
2. **Scalability**: Kafka is designed to handle high throughput and can scale horizontally. This means that as the number of users grows, the system can handle increased load without significant performance degradation.
3. **Event-Driven Architecture**: By integrating Kafka, the application can adopt an event-driven architecture. This allows different services to react to user events in real-time, enabling features like notifications, analytics, and auditing.
4. **Decoupling of Services**: Kafka can help decouple different components of the application. For example, the user management service can publish events to Kafka, and other services can subscribe to these events without direct dependencies.
5. **Data Consistency**: Kafka can help ensure data consistency across distributed systems by providing a reliable way to propagate user changes to other services.
6. **Complexity**: Integrating Kafka adds complexity to the system. Developers need to manage Kafka clusters, handle message serialization/deserialization, and ensure proper error handling and retries.
7. **Monitoring and Maintenance**: Kafka requires monitoring to ensure it is functioning correctly. This includes tracking message lag, broker health, and topic configurations.
Overall, integrating Kafka can enhance the capabilities of the User Management Service, but it also requires careful planning and management to handle the added complexity.

#### Application code impact
User Management service will now:

**a) Produce events**

Example:

- UserCreatedEvent

- UserUpdatedEvent

- UserDeletedEvent

```http
  userRepository.save(user)
  kafkaTemplate.send("user.created", userEvent)
```


**b) Possibly consume events**

Example:

- React to AccountVerifiedEvent

- React to UserDeactivatedEvent

```http
  @KafkaListener(topics = ["account.verified"])
  fun handle(event: AccountVerifiedEvent) { }
```


