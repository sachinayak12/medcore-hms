# ADR-001: Selection of Java 21 & Spring Boot 4.1.1

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

### Option 1 — Java 21 + Spring Boot 4.1.1

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

### Option 2 — Node.js (Express / NestJS)

**Advantages**

* Fast development
* Lightweight runtime
* Good for I/O-intensive applications

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
* Less aligned with existing team expertise

---

## 4. Decision

**MedCore HMS will use Java 21 with Spring Boot 4.1.1 as the backend technology stack.**

This combination provides long-term LTS support, a mature enterprise ecosystem, excellent Spring Cloud integration, strong security capabilities, and reliable transactional processing for healthcare applications.

---

## 5. Consequences

### Positive

* Strong Spring ecosystem for enterprise development
* Native support for RESTful APIs
* Seamless integration with Spring Security and Kafka
* Excellent testing and observability support
* Long-term maintainability

### Negative

* Higher JVM memory usage compared to Node.js
* Slightly steeper learning curve for new developers
* More infrastructure components in a microservices architecture

---

## 6. Impacted Components

The following services will be implemented using Java 21 and Spring Boot 4.1.1:

* API Gateway
* Eureka Server
* Config Server
* Auth Service
* Patient Service
* Appointment Service
* Billing Service
* Notification Service (Phase 2)

---

## 7. References

* Java 21 (LTS)
* Spring Boot 4.1.1
* Spring Security
* Spring Data JPA
* Spring Cloud
* Spring for Apache Kafka
