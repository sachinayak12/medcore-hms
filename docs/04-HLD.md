# High-Level Design (HLD)

**Project:** MedCore Hospital Management System (MedCore HMS)

**Version:** 1.0

**Architecture:** Microservices

**Author:** Sachidanand Nayak

---

# 1. Purpose

This document defines the overall architecture of MedCore HMS. It describes the system decomposition into microservices, communication patterns, infrastructure components, security model, and deployment strategy.

The HLD serves as the blueprint for detailed design and implementation.

---

# 2. System Overview

MedCore HMS is a distributed hospital management platform built using a microservices architecture.

Each business domain is implemented as an independent Spring Boot service with its own Microsoft SQL Server database. The frontend communicates through a single API Gateway, while services exchange business events using Apache Kafka.

---

# 3. Architecture Components

| Component               | Responsibility                      |
| ----------------------- | ----------------------------------- |
| Angular Web Application | User interface                      |
| API Gateway             | Entry point for all client requests |
| Eureka Server           | Service discovery                   |
| Config Server           | Centralized configuration           |
| Auth Service            | Authentication & JWT                |
| Patient Service         | Patient records                     |
| Appointment Service     | Scheduling                          |
| Billing Service         | Billing & payments                  |
| Kafka                   | Event-driven messaging              |
| MS SQL Server           | Database per service                |
| Redis                   | Caching (Phase 2)                   |

---

# 4. Service Boundaries

## Auth Service

Owns:

* Users
* Roles
* JWT Tokens

Database: **AuthDB**

---

## Patient Service

Owns:

* Patient Profile
* Medical History
* Emergency Contact

Database: **PatientDB**

---

## Appointment Service

Owns:

* Doctor Schedule
* Appointment Slots
* Booking Management

Database: **AppointmentDB**

---

## Billing Service

Owns:

* Invoice
* Payment
* Consultation Charges

Database: **BillingDB**

No service is allowed to access another service's database directly.

---

# 5. Communication Strategy

## Synchronous (REST)

Used when an immediate response is required.

Examples:

* Login
* Search Patient
* View Appointment
* Generate Bill

Protocol:

* HTTPS
* JSON
* REST

---

## Asynchronous (Kafka)

Used for background business events.

Example Events:

* PatientRegistered
* AppointmentCreated
* PaymentCompleted
* LabReportUploaded

Kafka enables loose coupling between services.

---

# 6. Security Architecture

Authentication Flow:

1. User logs in via API Gateway.
2. Gateway forwards request to Auth Service.
3. Auth Service validates credentials.
4. JWT token is generated.
5. Gateway validates JWT for subsequent requests.
6. Authorized request reaches target service.

Authorization Model:

| Role          | Modules                |
| ------------- | ---------------------- |
| Receptionist  | Patients, Appointments |
| Doctor        | Consultation           |
| Pharmacist    | Billing                |
| Administrator | All Services           |

---

# 7. Scalability Strategy

The system is designed for horizontal scalability.

Principles:

* Stateless Spring Boot services
* Independent deployments
* Database per service
* Event-driven communication
* Load balancer compatible
* Redis cache for frequently accessed data

---

# 8. Availability Strategy

| Component     | Strategy              |
| ------------- | --------------------- |
| API Gateway   | Stateless deployment  |
| Services      | Multiple instances    |
| Kafka         | Persistent event log  |
| SQL Server    | Regular backup        |
| Config Server | Central configuration |

---

# 9. Deployment Overview

Development Environment:

* Docker Compose
* SQL Server 2022
* Kafka
* Redis
* Eureka
* Config Server

Production readiness includes Kubernetes, centralized logging, distributed tracing, and CI/CD.

---

# 10. Design Principles

* Single Responsibility per Service
* Database per Service
* API First Development
* Event-Driven Integration
* Stateless Services
* Secure by Default
* No Shared Database

---

# 11. Future Enhancements

* Pharmacy Service
* Laboratory Service
* Notification Service
* Analytics Service
* Kubernetes Deployment
* ELK Logging
* Zipkin Distributed Tracing

---

**End of Document**
