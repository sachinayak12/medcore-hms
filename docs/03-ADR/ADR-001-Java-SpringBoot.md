# ADR-001: Selection of Java 21 & Spring Boot 3.5

**Status:** Accepted

**Date:** 2026-09-27

**Decision Makers:** Engineering Team

---

## 1. Context

MedCore HMS is an enterprise Hospital Management System that will be built using a microservices architecture. The backend must support:

* High concurrency for multiple hospital departments
* Secure authentication and authorization
* Transactional operations (billing & appointments)
* Scalable REST APIs
* Event-driven communication using Kafka
* Long-term maintainability

A backend framework must be selected before system design begins.

---

## 2. Decision Drivers

The selected technology should satisfy the following requirements:

| Requirement               | Importance |
| ------------------------- | ---------- |
| High performance          | High       |
| Enterprise ecosystem      | High       |
| Security support          | High       |
| Microservice support      | High       |
| Community & documentation | High       |
| Long-term support         | High       |

---

## 3. Options Considered

### Option 1 — Java 21 + Spring Boot 3.5

**Advantages**

* Mature enterprise ecosystem
* Excellent Spring Security support
* Strong transaction management
* Native Kafka integration
* Virtual Threads improve concurrency
* Widely adopted in banking and healthcare

**Disadvantages**

* Higher memory consumption than Node.js
* Steeper learning curve

---

### Option 2 — Node.js (Express/NestJS)

**Advantages**

* Fast development
* Lightweight runtime
* Good for I/O intensive applications

**Disadvantages**

* Weaker transaction ecosystem
* Less common in enterprise healthcare systems
* Different concurrency model

---

### Option 3 — ASP.NET Core

**Advantages**

* High performance
* Excellent tooling
* Strong enterprise support

**Disadvantages**

* Smaller Java ecosystem compatibility
* Less aligned with current team expertise

---

## 4. Decision

**We will use Java 21 with Spring Boot 3.5.**

This provides the best balance of scalability, security, transactional reliability, and enterprise tooling for MedCore HMS.

---

## 5. Consequences

### Positive

* Easy integration with Spring Security
* Native support for REST APIs
* Strong Kafka ecosystem
* Excellent testing support
* Production-ready observability

### Negative

* Higher JVM memory usage
* More boilerplate compared to Node.js

---

## 6. Impacted Components

* API Gateway
* Auth Service
* Patient Service
* Appointment Service
* Billing Service
* Notification Service

All backend services will use Java 21 and Spring Boot 3.5.

---

## 7. References

* Java 21 (LTS)
* Spring Boot 3.5
* Spring Security
* Spring Data JPA
* Spring for Apache Kafka
