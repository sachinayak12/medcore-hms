# ADR-003: Selection of Microsoft SQL Server

**Status:** Accepted

**Date:** 2026-09-27

**Decision Makers:** Engineering Team

---

## 1. Context

MedCore HMS requires a relational database capable of handling transactional healthcare data across multiple microservices. The system must ensure strong consistency, support complex queries, and integrate seamlessly with Spring Boot.

A database technology must be selected before designing the persistence layer.

---

## 2. Decision Drivers

| Requirement               | Priority |
| ------------------------- | -------- |
| ACID transactions         | High     |
| Referential integrity     | High     |
| Enterprise support        | High     |
| Spring Boot compatibility | High     |
| Scalability               | High     |
| Developer familiarity     | High     |

---

## 3. Options Considered

### Option 1 — Microsoft SQL Server

**Advantages**

* Excellent transactional consistency
* Mature query optimizer
* Strong indexing support
* Enterprise-grade security
* Native Docker image
* Familiar development experience

**Disadvantages**

* Higher resource consumption than some alternatives
* Licensing considerations in production (Developer Edition is free for development)

### Option 2 — PostgreSQL

**Advantages**

* Open source
* Excellent SQL compliance
* Strong community support

**Disadvantages**

* Less aligned with existing team experience

---

## 4. Decision

**MedCore HMS will use Microsoft SQL Server 2022.**

Each microservice will own an independent SQL Server database following the Database-per-Service pattern.

---

## 5. Database Ownership

| Microservice        | Database      |
| ------------------- | ------------- |
| Auth Service        | AuthDB        |
| Patient Service     | PatientDB     |
| Appointment Service | AppointmentDB |
| Billing Service     | BillingDB     |

No service is permitted to access another service's database directly.

---

## 6. Design Principles

* Primary keys use BIGINT identity.
* Foreign keys exist only within the same database.
* Cross-service relationships are maintained through APIs and events.
* All schema changes will be managed using Liquibase.

---

## 7. Consequences

### Positive

* Strong transactional reliability
* Excellent performance for relational workloads
* Familiar SQL ecosystem
* Easy integration with Spring Data JPA

### Negative

* Multiple databases increase operational complexity
* Cross-service joins are intentionally prohibited

---

## 8. Final Decision

Microsoft SQL Server 2022 is approved as the primary relational database for all MedCore HMS microservices.
