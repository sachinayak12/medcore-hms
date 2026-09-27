# ADR-002: Adoption of Microservices Architecture

**Status:** Accepted

**Date:** 2026-09-27

**Decision Makers:** Engineering Team

---

## 1. Context

MedCore HMS is a hospital management platform that includes multiple business domains such as Patient Management, Appointment Scheduling, Billing, Laboratory, Pharmacy, and Authentication.

The system is expected to grow over time, with each module evolving independently. An architectural decision is required to determine whether the application should be built as a Monolith or as Microservices.

---

## 2. Decision Drivers

The architecture should satisfy the following goals:

| Requirement                        | Priority |
| ---------------------------------- | -------- |
| Independent module development     | High     |
| Horizontal scalability             | High     |
| Fault isolation                    | High     |
| Independent deployment             | High     |
| Maintainability                    | High     |
| Support event-driven communication | High     |

---

## 3. Options Considered

### Option 1 — Modular Monolith

A single deployable application containing all business modules.

**Advantages**

* Simple development and debugging
* Single deployment pipeline
* Easier local setup
* Lower operational complexity

**Disadvantages**

* Tight coupling between modules
* Entire application must be redeployed for small changes
* Limited independent scalability
* Larger codebase over time

---

### Option 2 — Microservices

Each business domain is implemented as an independent Spring Boot service with its own database.

**Advantages**

* Independent deployment
* Database ownership per service
* Better scalability
* Fault isolation
* Supports event-driven architecture
* Clear domain boundaries

**Disadvantages**

* Higher operational complexity
* Service discovery required
* Distributed transactions become challenging
* More infrastructure components

---

## 4. Decision

**MedCore HMS will adopt a Microservices Architecture.**

The initial MVP will consist of four core services:

* Auth Service
* Patient Service
* Appointment Service
* Billing Service

Future services such as Pharmacy, Laboratory, Notification, and Analytics will be added without affecting existing services.

---

## 5. Architectural Principles

### 5.1 Database per Service

Each microservice owns its own PostgreSQL database.

| Service             | Database       |
| ------------------- | -------------- |
| Auth Service        | auth_db        |
| Patient Service     | patient_db     |
| Appointment Service | appointment_db |
| Billing Service     | billing_db     |

No service is allowed to directly access another service's database.

### 5.2 Communication Strategy

| Communication | Technology   |
| ------------- | ------------ |
| Synchronous   | REST APIs    |
| Asynchronous  | Apache Kafka |

REST will be used for immediate request/response operations, while Kafka will publish business events such as appointment creation and payment completion.

### 5.3 Service Boundaries

Each service is responsible for its own business logic, persistence, and APIs.

Example:

* Patient Service manages patient records.
* Billing Service manages invoices.
* Appointment Service manages schedules.

Cross-service communication must occur through APIs or events.

---

## 6. Consequences

### Positive

* Better separation of concerns
* Independent deployments
* Easier horizontal scaling
* Clear ownership of business domains
* Suitable for future cloud deployment

### Negative

* Increased infrastructure complexity
* More Docker containers
* Distributed logging required
* Network latency between services

---

## 7. Risks & Mitigation

| Risk                          | Mitigation                           |
| ----------------------------- | ------------------------------------ |
| Service communication failure | Retry & circuit breaker              |
| Event delivery issues         | Kafka persistent topics              |
| Data inconsistency            | Event-driven eventual consistency    |
| Multiple deployments          | Docker Compose for local development |

---

## 8. Future Considerations

The architecture will later introduce:

* Eureka Service Discovery
* Spring Cloud Gateway
* Config Server
* Distributed Tracing
* Centralized Logging
* Kubernetes deployment

These components are intentionally deferred until the core services are stable.

---

## 9. Final Decision

The engineering team approves **Microservices Architecture** as the foundational architecture for MedCore HMS because it best supports scalability, maintainability, independent deployment, and long-term product growth.
