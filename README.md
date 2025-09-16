# Product Pricing Service

This project is a Spring Boot application that provides price information via REST APIs. It is dockerized for easy deployment.

---

## Prerequisites

- Java 21
- Maven 3.9+
- Docker (for containerization)

---

## Building and deploying the application

To build the JAR file and run unit tests locally, use Maven inside de backend/product-pricing-service:

```bash
mvn clean package
```

In order to build the container, execute in the previous folder:

```bash
docker build -t pricing-service .
```

Now we are ready to deploy the containerized app with the following command:

```bash
docker run -p 8080:8080 pricing-service
```

Once deployed, the api is exposed and swagger UI is accessible from http://localhost:8080/swagger-ui/index.html

## Executing e2e tests

Once the dockerized app has been deployed, navigate from the root repository folder to 
e2e-tests/product-pricing-service-e2e. Once there you can execute the e2e tests with the following command:
```bash
mvn test
```