# Fitness Tracking App

A REST API for registering users, recording fitness activities, and managing activity recommendations. The API is built with Spring Boot and exposes interactive OpenAPI documentation through Swagger UI.

## Live application

- **Swagger UI:** [fitness-tracking-app-kiqr.onrender.com/swagger-ui/index.html](https://fitness-tracking-app-kiqr.onrender.com/swagger-ui/index.html)
- **API base URL:** `https://fitness-tracking-app-kiqr.onrender.com`

Use Swagger UI to inspect request and response schemas and try the endpoints against the deployed API.

## Features

- User registration and login with JWT authentication
- Activity tracking and retrieval
- Fitness recommendation creation and retrieval
- Request validation and centralized exception handling
- OpenAPI documentation with bearer-token authorization
- MySQL persistence through Spring Data JPA

## API endpoints

| Method | Endpoint | Description | Access |
| --- | --- | --- | --- |
| `POST` | `/api/auth/register` | Register a user | Public |
| `POST` | `/api/auth/login` | Log in and receive a JWT | Public |
| `POST` | `/api/activities` | Record an activity | JWT required |
| `GET` | `/api/activities` | Retrieve user activities (`X-User-ID` header) | JWT required |
| `POST` | `/api/recommendation/generate` | Create a recommendation | JWT required |
| `GET` | `/api/recommendation/user/{userId}` | Retrieve recommendations for a user | JWT required |
| `GET` | `/api/recommendation/activity/{activityId}` | Retrieve recommendations for an activity | JWT required |

For protected endpoints, send the login token in the `Authorization` header as `Bearer <token>`. The activity-list endpoint also requires the `X-User-ID` header.

## Technology stack

- Java 17
- Spring Boot
- Spring Web MVC, Spring Security, Spring Data JPA, and Jakarta Bean Validation
- MySQL
- JSON Web Tokens (JJWT)
- springdoc-openapi / Swagger UI
- Maven

## Run locally

### Prerequisites

- JDK 17
- A reachable MySQL database

Configure these environment variables before starting the app:

| Variable | Purpose |
| --- | --- |
| `DB_URL` | JDBC URL for the MySQL database |
| `DB_USER` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `JWT_SECRET` | Base64-encoded signing key for JWTs |

Keep database credentials and the JWT signing key out of source control. Use a strong, private Base64-encoded JWT key.

Start the application using the Maven wrapper:

```bash
# macOS / Linux
./mvnw spring-boot:run

# Windows PowerShell
.\mvnw.cmd spring-boot:run
```

The server listens on port `8080` by default. Set `PORT` to change it. Once running, open `http://localhost:8080/swagger-ui/index.html`.

To build and test:

```bash
./mvnw test
./mvnw clean package
```

On Windows, use `.\mvnw.cmd` in place of `./mvnw`.

## Docker

Build the executable JAR, then build and run the image from the repository root:

```bash
./mvnw clean package -DskipTests
docker build -t fitness-tracking-app .
docker run --rm -p 8080:8080 \
  -e DB_URL="jdbc:mysql://host.docker.internal:3306/fitness" \
  -e DB_USER="your-user" \
  -e SPRING_DATASOURCE_PASSWORD="your-password" \
  -e JWT_SECRET="your-base64-encoded-secret" \
  fitness-tracking-app
```

Replace the example database URL and credentials with values for your environment. The container listens on port `8080` by default and accepts a different port through `PORT`.

## Project structure

```text
src/
  main/java/com/project/fitness/
    configuration/  OpenAPI configuration
    controller/     Authentication, activity, and recommendation endpoints
    dto/            API request and response objects
    exceptions/     Global exception handling
    model/          JPA entities and enums
    repository/     Spring Data repositories
    security/       JWT authentication and security configuration
    service/        Application services
  main/resources/   Application configuration
  test/             Automated tests
```
