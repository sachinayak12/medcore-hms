# API Specification (OpenAPI)

**Project:** MedCore Hospital Management System

**Version:** 1.0

**Architecture:** API-First

---

# 1. API Standards

| Property       | Value            |
| -------------- | ---------------- |
| Protocol       | HTTPS            |
| Format         | JSON             |
| Authentication | JWT Bearer Token |
| Base URL       | `/api/v1`        |

---

# 2. Authentication APIs

## POST /api/v1/auth/login

Authenticates a user and returns a JWT.

### Request

```json
{
  "username": "reception01",
  "password": "Password@123"
}
```

### Success Response (200)

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIs...",
  "tokenType": "Bearer",
  "expiresIn": 1800,
  "roles": [
    "RECEPTIONIST"
  ]
}
```

### Error Response (401)

```json
{
  "message": "Invalid username or password"
}
```

---

# 3. Patient APIs

## POST /api/v1/patients

Creates a new patient.

### Request

```json
{
  "firstName": "Rahul",
  "lastName": "Sharma",
  "dob": "1998-04-15",
  "gender": "M",
  "phone": "9876543210",
  "bloodGroup": "O+"
}
```

### Response (201)

```json
{
  "patientId": 10001,
  "patientNumber": "PAT-10001",
  "message": "Patient registered successfully"
}
```

---

## GET /api/v1/patients/{patientId}

Returns patient details.

### Response

```json
{
  "patientId": 10001,
  "patientNumber": "PAT-10001",
  "firstName": "Rahul",
  "lastName": "Sharma",
  "phone": "9876543210",
  "bloodGroup": "O+"
}
```

---

## GET /api/v1/patients/search

Search by phone or name.

### Example

`GET /patients/search?phone=9876543210`

---

# 4. Appointment APIs

## POST /api/v1/appointments

Books an appointment.

### Request

```json
{
  "patientId": 10001,
  "doctorId": 12,
  "appointmentDate": "2026-10-02T10:30:00"
}
```

### Response

```json
{
  "appointmentId": 5001,
  "status": "BOOKED"
}
```

---

## PUT /api/v1/appointments/{id}/cancel

Cancels an appointment.

### Response

```json
{
  "status": "CANCELLED"
}
```

---

# 5. Billing APIs

## POST /api/v1/billing/invoices

Generates an invoice.

### Request

```json
{
  "appointmentId": 5001
}
```

### Response

```json
{
  "invoiceId": 7001,
  "invoiceNumber": "INV-7001",
  "totalAmount": 850.00
}
```

---

# 6. HTTP Status Codes

| Code | Meaning               |
| ---- | --------------------- |
| 200  | Success               |
| 201  | Created               |
| 400  | Validation Error      |
| 401  | Unauthorized          |
| 403  | Forbidden             |
| 404  | Resource Not Found    |
| 409  | Business Conflict     |
| 500  | Internal Server Error |

---

# 7. Common Headers

Authorization:

```text
Bearer <JWT_TOKEN>
```

Content-Type:

```text
application/json
```

Accept:

```text
application/json
```

---

# 8. API Versioning

Current Version:

```text
/api/v1
```

Future versions will follow URI versioning:

* `/api/v2`
* `/api/v3`

without breaking existing clients.

---

# 9. Error Response Format

```json
{
  "timestamp": "2026-09-27T10:30:00Z",
  "status": 400,
  "error": "Validation Error",
  "message": "Phone number already exists",
  "path": "/api/v1/patients"
}
```

All services will return a consistent error structure.

---

**End of Document**
