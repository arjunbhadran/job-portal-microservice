# Job Portal Microservice

A cloud-native **Job Portal** application built with Spring Boot microservices, featuring centralized configuration and service discovery using Spring Cloud Config Server and Eureka. The system is modularized into distinct services for jobs, companies, and reviews, enabling scalability, maintainability, and independent deployment.

---

## 🛠️ Tech Stack

- **Spring Boot** (Microservices)
- **Spring Cloud Netflix Eureka** (Service Discovery)
- **Spring Cloud Config Server** (Centralized Configuration)
- **Docker** (Containerization)
- **Java 17+** (or your chosen version)
- **Maven/Gradle** (Build Tool)

---

## 📦 Project Structure

```

job-portal-microservice/
│
├── company/         \# Company microservice
├── configserver/    \# Spring Cloud Config Server
├── jobs/            \# Job microservice
├── review/          \# Review microservice
├── serv-reg/        \# Eureka Service Registry
└── README.md

```

---

## 🧩 Microservices Overview

| Service      | Description                                                  | Default Port (suggested) |
|--------------|-------------------------------------------------------------|--------------------------|
| serv-reg     | Eureka Server for service discovery                         | 8761                     |
| configserver | Centralized config server for all microservices             | 8888                     |
| company      | Manages company-related data and operations                 | 8081                     |
| jobs         | Handles job postings and related business logic             | 8082                     |
| review       | Manages company/job reviews and ratings                     | 8083                     |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- Docker (optional, for containerized deployment)

### 1. Clone the Repository

```

git clone https://github.com/your-username/job-portal-microservice.git
cd job-portal-microservice

```

### 2. Configuration

- **Config Server**: Place your configuration files in a Git repo or local directory as required by Spring Cloud Config.
- **Update application.yml/bootstrap.yml** in each service to point to the config server and Eureka server.

### 3. Build All Services

```


# From the root directory, build each service

cd configserver \&\& mvn clean package
cd ../serv-reg \&\& mvn clean package
cd ../company \&\& mvn clean package
cd ../jobs \&\& mvn clean package
cd ../review \&\& mvn clean package

```

### 4. Run the Services

**Order of startup:**
1. Eureka Server (`serv-reg`)
2. Config Server (`configserver`)
3. Company, Jobs, and Review microservices

```


# Example for running Eureka server

cd serv-reg \&\& java -jar target/serv-reg-*.jar

# Then Config Server

cd ../configserver \&\& java -jar target/configserver-*.jar

# Then the other services (in any order)

cd ../company \&\& java -jar target/company-*.jar
cd ../jobs \&\& java -jar target/jobs-*.jar
cd ../review \&\& java -jar target/review-*.jar

```

### 5. Docker (Optional)

To run everything in Docker, create Dockerfiles for each service and use a `docker-compose.yml` to orchestrate startup and networking.

---

## 🌐 Service Endpoints

- **Eureka Dashboard:** `http://localhost:8761`
- **Config Server:** `http://localhost:8888`
- **Company Service:** `http://localhost:8081`
- **Jobs Service:** `http://localhost:8082`
- **Review Service:** `http://localhost:8083`

---

## ✨ Features

- Centralized configuration management for all services
- Service discovery and registration with Eureka
- Modular, independently deployable microservices
- Easily extensible for new features (e.g., authentication, API gateway)

---

## 🛡️ Best Practices

- Use parameterized queries (JPA/PreparedStatements) to avoid injection attacks
- Secure endpoints with Spring Security (JWT/OAuth2 recommended)
- Regularly update third-party dependencies and scan for vulnerabilities
- Externalize all sensitive configuration (use config server and environment variables)

---

## 🤝 Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

---

## 📄 License

This project is licensed under the MIT License.

---

## 📢 Acknowledgements

- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Netflix OSS](https://netflix.github.io/)
```

