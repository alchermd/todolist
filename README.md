# Spring + PostgreSQL + Vue TodoList

Barebones CRUD application that I built to get the hang of the Spring framework.

## Installation

1.) Create a postgreSQL database:

```bash
$ createdb todolist
```

2.) Create an `application.properties` file:

```bash
$ touch src/main/resources/application.properties
```

... and fill in your database credentials:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
```

This application is built with Spring Boot, so you could just use the included Maven wrapper to start the HTTP server:

```bash
$ ./mvnw spring-boot:run
```

You can now visit the app at `http://localhost:8080/`