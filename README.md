# mcp-server
I developed a Java backend service for CRUD operations on a User entity, using MCP client and server components. Users can issue natural language prompts, which the system interprets and executes as valid CRUD actions, offering an intuitive interface for managing user data.

# MCP User Management API

This project provides a RESTful API for managing users with CRUD operations, integrated with an NLP-based OpenAI client for advanced operations. The API is built using **Spring Boot**, **Jakarta Validation**, and **Spring Data JPA**.

---

## Features

- Create, read, update, and delete users
- Retrieve users by ID or email
- NLP-based CRUD operations via OpenAI client
- Validation using Jakarta Bean Validation
- DTO-based responses for consistent API outputs

---

## Requirements

- **Java 17+**
- **Maven 3.8+**
- **Spring Boot 3.x**
- PostgreSQL or other supported relational database
- OpenAI API key

---

## Environment Setup

### 1. Clone the repository

git clone https://github.com/MrCoolChips/mcp_server.git

cd mcp-server

## 2. Environment Variables

You can create a .env file in the root folder or use application.yml in src/main/resources.

## 3. Build and Run

1. Build the project
mvn clean install

2. Run the project
mvn spring-boot:run

The API will be available at http://localhost:8080

## API Endpoints

| Method | Endpoint         | Description         |
| ------ | ---------------- | ------------------- |
| POST   | `/users`         | Create a new user   |
| GET    | `/users`         | Get all users       |
| GET    | `/users/{id}`    | Get user by UUID    |
| GET    | `/users/by-mail` | Get user by email   |
| PUT    | `/users/{id}`    | Update user by UUID |
| DELETE | `/users/{id}`    | Delete user by UUID |

## NLP CRUD (OpenAI)

| Method | Endpoint     | Description                       |
| ------ | ------------ | --------------------------------- |
| POST   | `/admin/nlp` | Process NLP-based user operations |

The NLP endpoint expects a JSON payload with a prompt field describing the operation (create, get, update, delete). This endpoint uses @PostMapping("/nlp") in the McpCompletionToolController class. It handles POST requests and passes the request body to NlpCrudService.

## Example JSON Payloads

1) Create User
{
  "prompt": "Create a user with name 'John Doe', mail 'john@example.com', age 30"
}

2) Get User by ID
{
  "prompt": "Get user with id '550e8400-e29b-41d4-a716-446655440000'"
}

3) Update User
{
  "prompt": "Update user with id '550e8400-e29b-41d4-a716-446655440000', set age to 35"
}

4) Delete User
{
  "prompt": "Delete user with id '550e8400-e29b-41d4-a716-446655440000'"
}

## Notes

* Ensure your OpenAI API key is valid and has sufficient quota.
* DTOs are used to enforce validation and provide consistent API outputs.
* Transactions are used in services to ensure database consistency.
* The UserServiceImpl class implements CRUD operations and converts entities to DTOs.
* The NlpCrudService class parses natural language prompts, calls OpenAI, and performs CRUD operations automatically.
* The OpenAI client sends a system message to ensure JSON-only output, handles errors, and maps responses to DTOs.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
