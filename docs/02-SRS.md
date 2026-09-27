# Software Requirements Specification (SRS)

**Project:** MedCore Hospital Management System (MedCore HMS)

**Version:** 1.0

**Status:** Draft

**Architecture:** Microservices

**Author:** Sachidanand Nayak

---

# 1. Purpose

The purpose of this Software Requirements Specification (SRS) is to define the functional and non-functional requirements for MedCore Hospital Management System. This document serves as the primary reference for system architecture, development, testing, and project acceptance.

---

# 2. Scope

MedCore HMS is a centralized hospital management platform designed to streamline patient registration, appointment scheduling, doctor consultations, billing, laboratory, and pharmacy operations.

## In Scope (MVP)

* Patient Management
* Appointment Scheduling
* Doctor Consultation
* Electronic Medical Records
* Billing
* User Authentication & Authorization
* Audit Logging

## Out of Scope

* Insurance Claims
* Online Payment Gateway
* Telemedicine
* Mobile Application
* AI Diagnosis

---

# 3. User Roles

| Role           | Responsibilities                               |
| -------------- | ---------------------------------------------- |
| Receptionist   | Register patients, manage appointments         |
| Doctor         | Consultation, diagnosis, prescriptions         |
| Lab Technician | Upload laboratory reports                      |
| Pharmacist     | Dispense medicines and manage stock            |
| Administrator  | User management, reports, system configuration |
| Patient        | View appointments and medical reports          |

---

# 4. Functional Requirements

## 4.1 Patient Management

| ID     | Requirement                         | Priority |
| ------ | ----------------------------------- | -------- |
| FR-001 | Register a new patient              | High     |
| FR-002 | Update patient information          | High     |
| FR-003 | Search patient by ID, name or phone | High     |
| FR-004 | View patient medical history        | High     |
| FR-005 | Deactivate duplicate patient record | Medium   |

## 4.2 Appointment Management

| ID     | Requirement                    | Priority |
| ------ | ------------------------------ | -------- |
| FR-006 | Book appointment               | High     |
| FR-007 | Cancel appointment             | High     |
| FR-008 | Reschedule appointment         | High     |
| FR-009 | Check doctor availability      | High     |
| FR-010 | Prevent duplicate slot booking | High     |

## 4.3 Doctor Consultation

| ID     | Requirement                   | Priority |
| ------ | ----------------------------- | -------- |
| FR-011 | Record diagnosis              | High     |
| FR-012 | Create prescription           | High     |
| FR-013 | Request laboratory test       | Medium   |
| FR-014 | Generate consultation summary | Medium   |

## 4.4 Billing

| ID     | Requirement                    | Priority |
| ------ | ------------------------------ | -------- |
| FR-015 | Generate invoice               | High     |
| FR-016 | Calculate consultation charges | High     |
| FR-017 | Record patient payment         | High     |

## 4.5 Administration

| ID     | Requirement                   | Priority |
| ------ | ----------------------------- | -------- |
| FR-018 | User authentication           | High     |
| FR-019 | Role-based authorization      | High     |
| FR-020 | Audit every data modification | High     |

---

# 5. Business Rules

| ID     | Rule                                                         |
| ------ | ------------------------------------------------------------ |
| BR-001 | Patient mobile number must be unique.                        |
| BR-002 | A doctor cannot have two appointments in the same time slot. |
| BR-003 | Cancelled appointments cannot be billed.                     |
| BR-004 | Only doctors can create prescriptions.                       |
| BR-005 | Only administrators can create new users.                    |

---

# 6. User Stories

## US-001 — Patient Registration

**As a** Receptionist

**I want to** register a new patient

**So that** appointments can be scheduled.

### Acceptance Criteria

* Patient ID is generated automatically.
* Mobile number is validated.
* Duplicate mobile numbers are rejected.
* Audit log is created.

---

## US-002 — Book Appointment

**As a** Receptionist

**I want to** schedule an appointment

**So that** the patient can consult a doctor.

### Acceptance Criteria

* Doctor availability is verified.
* Time slot cannot be double booked.
* Appointment confirmation is generated.

---

## US-003 — Doctor Consultation

**As a** Doctor

**I want to** record diagnosis and prescriptions

**So that** patient history is maintained digitally.

### Acceptance Criteria

* Diagnosis is saved.
* Prescription is linked to the appointment.
* Timestamp and doctor ID are recorded.

---

# 7. Non-Functional Requirements

| ID      | Requirement          | Target                      |
| ------- | -------------------- | --------------------------- |
| NFR-001 | API Response Time    | Less than 300 ms            |
| NFR-002 | Availability         | 99.9%                       |
| NFR-003 | Authentication       | JWT                         |
| NFR-004 | Authorization        | Role-Based Access Control   |
| NFR-005 | Scalability          | Horizontal Scaling          |
| NFR-006 | Database Consistency | ACID Compliance             |
| NFR-007 | Audit Coverage       | 100% of critical operations |
| NFR-008 | Logging              | Centralized structured logs |

---

# 8. Assumptions

* Internet connectivity is available.
* Every doctor has predefined working hours.
* A patient may have multiple appointments.
* One appointment belongs to one doctor.
* Each hospital user has a unique login account.

---

# 9. Constraints

* Microservices architecture
* Database-per-service pattern
* REST for synchronous communication
* Kafka for asynchronous events
* PostgreSQL as the primary database
* Docker for local development and deployment

---

# 10. Success Criteria

| KPI                       | Target          |
| ------------------------- | --------------- |
| Patient Registration Time | Under 2 minutes |
| Appointment Conflicts     | Zero            |
| Average API Response      | Under 300 ms    |
| Billing Accuracy          | 100%            |
| Audit Logging Coverage    | 100%            |

---

**End of Document**
