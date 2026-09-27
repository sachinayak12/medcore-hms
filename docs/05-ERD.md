# Entity Relationship Design (ERD)

**Project:** MedCore Hospital Management System

**Version:** 1.0

**Database:** Microsoft SQL Server 2022

**Architecture:** Database per Service

---

# 1. Purpose

This document defines the logical database design for MedCore HMS.

Each microservice owns an independent SQL Server database. Cross-service relationships are maintained through APIs and Kafka events rather than foreign keys.

---

# 2. Database Strategy

| Microservice        | Database      |
| ------------------- | ------------- |
| Auth Service        | AuthDB        |
| Patient Service     | PatientDB     |
| Appointment Service | AppointmentDB |
| Billing Service     | BillingDB     |

**Design Principle**

* One database per microservice
* No cross-database foreign keys
* BIGINT identity primary keys
* Soft delete using IsDeleted flag
* Audit fields on every table

---

# 3. AuthDB

## users

| Column       | Type         | Constraint |
| ------------ | ------------ | ---------- |
| UserId       | BIGINT       | PK         |
| Username     | VARCHAR(50)  | UNIQUE     |
| PasswordHash | VARCHAR(255) | NOT NULL   |
| Email        | VARCHAR(100) | UNIQUE     |
| IsActive     | BIT          | DEFAULT 1  |
| CreatedAt    | DATETIME2    | NOT NULL   |

## roles

| Column   | Type        |
| -------- | ----------- |
| RoleId   | BIGINT      |
| RoleName | VARCHAR(50) |

## user_roles

Many-to-many mapping between users and roles.

| Column | Type   |
| ------ | ------ |
| UserId | BIGINT |
| RoleId | BIGINT |

---

# 4. PatientDB

## patients

| Column        | Type        | Constraint |
| ------------- | ----------- | ---------- |
| PatientId     | BIGINT      | PK         |
| PatientNumber | VARCHAR(20) | UNIQUE     |
| FirstName     | VARCHAR(50) |            |
| LastName      | VARCHAR(50) |            |
| DOB           | DATE        |            |
| Gender        | CHAR(1)     |            |
| Phone         | VARCHAR(15) | UNIQUE     |
| BloodGroup    | VARCHAR(5)  |            |
| IsDeleted     | BIT         | DEFAULT 0  |

## patient_address

| Column    | Type        |
| --------- | ----------- |
| AddressId | BIGINT      |
| PatientId | BIGINT      |
| City      | VARCHAR(50) |
| State     | VARCHAR(50) |
| Pincode   | VARCHAR(10) |

Relationship:

* One Patient → Many Addresses

## medical_history

| Column    | Type         |
| --------- | ------------ |
| HistoryId | BIGINT       |
| PatientId | BIGINT       |
| Allergy   | VARCHAR(100) |
| Disease   | VARCHAR(100) |
| Notes     | VARCHAR(MAX) |

Relationship:

* One Patient → Many Medical Records

---

# 5. AppointmentDB

## doctors

| Column         | Type         |
| -------------- | ------------ |
| DoctorId       | BIGINT       |
| Name           | VARCHAR(100) |
| Specialization | VARCHAR(100) |
| Department     | VARCHAR(50)  |

## appointments

| Column          | Type        |
| --------------- | ----------- |
| AppointmentId   | BIGINT      |
| PatientId       | BIGINT      |
| DoctorId        | BIGINT      |
| AppointmentDate | DATETIME2   |
| Status          | VARCHAR(20) |

Business Rule:

A doctor cannot have two appointments in the same time slot.

## consultation

| Column         | Type         |
| -------------- | ------------ |
| ConsultationId | BIGINT       |
| AppointmentId  | BIGINT       |
| Diagnosis      | VARCHAR(MAX) |
| Prescription   | VARCHAR(MAX) |

---

# 6. BillingDB

## invoices

| Column        | Type          |
| ------------- | ------------- |
| InvoiceId     | BIGINT        |
| AppointmentId | BIGINT        |
| InvoiceNumber | VARCHAR(30)   |
| TotalAmount   | DECIMAL(10,2) |
| Status        | VARCHAR(20)   |

## invoice_items

| Column      | Type          |
| ----------- | ------------- |
| ItemId      | BIGINT        |
| InvoiceId   | BIGINT        |
| Description | VARCHAR(100)  |
| Amount      | DECIMAL(10,2) |

Relationship:

* One Invoice → Many Invoice Items

## payments

| Column      | Type          |
| ----------- | ------------- |
| PaymentId   | BIGINT        |
| InvoiceId   | BIGINT        |
| Amount      | DECIMAL(10,2) |
| PaymentMode | VARCHAR(30)   |
| PaidAt      | DATETIME2     |

---

# 7. Common Audit Columns

Every table includes the following fields:

| Column    | Purpose           |
| --------- | ----------------- |
| CreatedAt | Record creation   |
| CreatedBy | User ID           |
| UpdatedAt | Last modification |
| UpdatedBy | User ID           |
| IsDeleted | Soft delete flag  |

---

# 8. Index Strategy

| Table        | Index                      |
| ------------ | -------------------------- |
| patients     | Phone, PatientNumber       |
| appointments | DoctorId + AppointmentDate |
| users        | Username                   |
| invoices     | InvoiceNumber              |

---

# 9. Normalization

The database follows Third Normal Form (3NF):

* No repeating groups
* No partial dependencies
* No transitive dependencies

Reference data (roles, departments, status) is separated into dedicated tables where appropriate.

---

**End of Document**
