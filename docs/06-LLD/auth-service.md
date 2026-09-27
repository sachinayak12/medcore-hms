# Low-Level Design (LLD) — Auth Service

**Project:** MedCore Hospital Management System

**Microservice:** Auth Service

**Version:** 1.0

---

# 1. Purpose

The Auth Service is responsible for authentication, authorization, JWT token generation, and user role management.

It is the only service that manages users and roles.

---

# 2. Responsibilities

* User Login
* JWT Generation
* Password Verification
* Role Management
* Token Validation
* User Creation (Admin)

---

# 3. Package Structure

```text
auth-service
└── src/main/java
    └── com.medcore.auth
        ├── controller
        ├── service
        ├── repository
        ├── entity
        ├── dto
        ├── security
        ├── config
        ├── exception
        └── util
```

Each package has a single responsibility.

---

# 4. Layered Architecture

Controller

↓

Service

↓

Repository

↓

SQL Server

The Controller never communicates directly with the Repository.

---

# 5. Main Components

## AuthController

Responsibilities:

* POST /login
* POST /validate
* POST /users

Calls only AuthService.

---

## AuthService

Business logic:

* Verify username
* Compare password hash
* Generate JWT
* Fetch user roles

---

## UserRepository

Responsible only for database operations.

Examples:

* findByUsername()
* save()
* existsByUsername()

---

## JwtService

Responsibilities:

* Create JWT
* Validate JWT
* Extract username
* Extract roles

No business logic belongs here.

---

# 6. DTO Design

## LoginRequest

| Field    | Type   |
| -------- | ------ |
| username | String |
| password | String |

## LoginResponse

| Field       | Type         |
| ----------- | ------------ |
| accessToken | String       |
| username    | String       |
| roles       | List<String> |
| expiresAt   | Long         |

DTOs prevent exposing database entities directly.

---

# 7. Database Ownership

Database: **AuthDB**

Tables:

* Users
* Roles
* UserRoles

The Auth Service owns these tables exclusively.

---

# 8. Login Sequence

1. Client sends username/password.
2. API Gateway forwards request.
3. AuthController receives request.
4. AuthService validates credentials.
5. UserRepository fetches user.
6. JwtService generates token.
7. Response returned to client.

---

# 9. Error Handling

| Scenario              | HTTP |
| --------------------- | ---- |
| Invalid credentials   | 401  |
| User disabled         | 403  |
| Validation failure    | 400  |
| Internal server error | 500  |

---

# 10. Security Notes

* Passwords stored using BCrypt
* JWT expiration: 30 minutes
* Refresh token planned for Phase 2
* Role-Based Access Control (RBAC)

---

# 11. Future Enhancements

* Refresh Tokens
* MFA Authentication
* Password Reset
* Account Lock after failed attempts

---

**End of Document**
