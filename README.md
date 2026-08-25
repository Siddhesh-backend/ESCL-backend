ESCL — Engineering Sportsclub Championship League

Backend

The ESCL Backend is a Spring Boot REST API developed for the Engineering Sports Championship League cricket tournament platform.

It provides backend services for player management, authentication, tournament-related data, and communication with the frontend application.

Tech Stack

Java

Spring Boot

Spring Data JPA

MySQL

Maven

REST APIs

Session-based Authentication

IntelliJ IDEA

Git & GitHub

Postman

Project Architecture

The application follows a layered architecture.

src/main/java/com/escl
├── config
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
├── service
└── EsclApplication.java

Package Responsibilities

Package

Responsibility

config

Application configuration

controller

REST API endpoints

dto

Data Transfer Objects

exception

Exception handling

mapper

Mapping between DTOs and entities

model

Database entity classes

repository

Database access using Spring Data JPA

service

Business logic

EsclApplication

Spring Boot application entry point

Database

MySQL is used as the primary relational database.

Spring Data JPA is used for database interaction and persistence.

The repository layer handles database operations through Spring Data JPA.

Authentication & Authorization

The application uses session-based authentication for user authentication and access control.

After successful authentication, the user's authenticated session is maintained by the server and used to protect restricted resources.

The application also supports role-based access for administrative functionality.

REST API

The backend exposes RESTful APIs consumed by the ESCL frontend application.

The API layer is implemented using Spring Boot controllers and uses DTOs to structure request and response data.

Error Handling

The application contains a dedicated exception layer for handling application errors and providing consistent error responses.

Maven Configuration

The project uses Maven for dependency management and build automation.

The main Maven configuration is:

pom.xml

Running the Project Locally

1. Clone the repository

git clone <repository-url>

2. Open the project

Open the project using IntelliJ IDEA or another Java-compatible IDE.

3. Configure the database

Create the required MySQL database and configure the database properties in:

src/main/resources/application.properties

4. Build the project

mvn clean install

5. Run the application

Run:

EsclApplication.java

The Spring Boot application will start on the configured server port.

API Testing

The backend APIs can be tested using Postman.

API requests can be tested independently before integrating the backend with the frontend application.

Project Structure

ESCL
├── .mvn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.escl
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── exception
│   │   │       ├── mapper
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── EsclApplication.java
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties
│   └── test
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

Frontend Integration

The backend is designed to work with the ESCL React frontend through REST APIs.

The frontend communicates with the backend using HTTP requests and consumes the exposed API endpoints.

Deployment

The application is configured to support deployment in a production environment.

Environment-specific configuration should be maintained separately from the source code.

Project Status

The ESCL Backend is an actively developed backend application for the Engineering Sports Championship League platform.

Author

Siddhesh Bhavsar

Engineering Sportsclub Championship League (ESCL)