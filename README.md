# sky-ledger-services

sky-ledger-services is a Spring Boot backend service for the Sky Ledger platform.  
It exposes REST APIs for core business modules and integrates with database.

---

## Tech Stack

- Java (17)
- Spring Boot (3.9)
- Maven
- Spring Web (REST APIs)
- Spring Data JPA (DB access)
- Lombok (optional)
- Database: PostgreSQL (recommended)

---

## Must Follow

Install the following:

- JDK 17 (recommended for Spring Boot 3.5 projects)
- Maven 3.9+
- Git
- Database (PostgreSQL) running locally OR accessible via connection string

Verify:

```bash
java -version
mvn -version
```

## Before raising a PR validate this 
```bash
docker build -t sky-ledger-services:latest --build-arg SN_GITHUB_USERNAME=$SN_GITHUB_USERNAME --build-arg SN_GITHUB_TOKEN=$SN_GITHUB_TOKEN .
```
Then
```bash
docker run -it --rm --name sky-ledger -p 9090:9090 -p 8081:8081 sky-ledger-services:latest
```
Test
