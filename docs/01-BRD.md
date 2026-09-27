# Business Requirements Document (BRD)

> **Project:** MedCore Hospital Management System (MedCore HMS)

---

## 1. Document Information

| Field | Value |
|--------|-------|
| Project | MedCore HMS |
| Document | Business Requirements Document |
| Version | 1.0 |
| Status | Draft |
| Author | Sachidanand Nayak |
| Architecture | Microservices |

---

## 2. Purpose

The purpose of MedCore HMS is to provide a centralized Hospital Management System that digitizes patient registration, appointment scheduling, doctor consultation, laboratory, pharmacy and billing operations.

The platform should allow multiple hospital departments to collaborate securely while maintaining complete audit history and scalable architecture.

---

## 3. Business Problem

Many small and medium hospitals still use disconnected software or manual processes.

This leads to:

- Duplicate patient records
- Appointment conflicts
- Delayed billing
- Poor inter-department communication
- Lack of centralized medical history
- Difficult operational reporting

MedCore HMS aims to eliminate these problems through a unified digital platform.

---

## 4. Business Objectives

The system should:

1. Digitize patient registration.
2. Prevent appointment conflicts.
3. Maintain electronic medical records.
4. Generate accurate invoices.
5. Integrate laboratory and pharmacy workflows.
6. Maintain complete audit trails.
7. Support future horizontal scaling.

---

## 5. Stakeholders

| Stakeholder | Responsibility |
|-------------|----------------|
| Receptionist | Register patients & book appointments |
| Doctor | Consultation & prescriptions |
| Lab Technician | Upload laboratory reports |
| Pharmacist | Dispense medicines |
| Administrator | User management & analytics |
| Patient | View appointments & reports |

---

## 6. Project Scope

### In Scope (MVP)

- Patient Registration
- Appointment Scheduling
- Doctor Consultation
- Electronic Medical Records
- Billing
- User Authentication
- Audit Logging

### Out of Scope

- Insurance Claims
- Online Payments
- Telemedicine
- Mobile Application
- AI Diagnosis

---

## 7. Business Workflow

Patient Registration

↓

Appointment Booking

↓

Doctor Consultation

↓

Lab Test / Pharmacy

↓

Billing

↓

Discharge

---

## 8. Success Metrics

| KPI | Target |
|------|--------|
| Patient Registration | Under 2 minutes |
| Appointment Conflict | Zero |
| API Response Time | Under 300 ms |
| Billing Accuracy | 100% |
| Audit Coverage | 100% |

---

## 9. Business Risks

| Risk | Mitigation |
|------|------------|
| Duplicate Patients | Unique mobile number validation |
| Double Booking | Slot validation |
| Unauthorized Access | JWT + RBAC |
| Data Loss | Automated database backups |
| Slow Dashboard | Redis caching |

---

## 10. Future Roadmap

### Phase 2

- Insurance Module
- Inventory Management

### Phase 3

- Patient Mobile App
- Online Payments

### Phase 4

- AI Assisted Diagnosis
- Predictive Analytics