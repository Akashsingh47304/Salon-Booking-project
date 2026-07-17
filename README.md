# 💇 Salon Booking Microservices Platform

<img width="1536" height="1024" alt="ChatGPT Image Jul 17, 2026, 12_28_59 PM" src="https://github.com/user-attachments/assets/a4a5c56b-44ec-44b4-9ea0-33dad377eab3" />


<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-6DB33F?style=for-the-badge&logo=springboot)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.0.0-6DB33F?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-purple?style=for-the-badge)

</p>

---

# 📖 Overview

Salon Booking is a **Spring Boot Microservices** application that enables users to discover salons, browse available services, book appointments, and securely process payments.

The application demonstrates a production-style distributed architecture using **Spring Cloud Gateway**, **Netflix Eureka**, **Spring Security**, **JWT Authentication**, and separate databases for each microservice.

---

# ✨ Features

### 👤 User Service

- User Registration
- Secure Login
- JWT Authentication
- Role Based Authorization

### 💇 Salon Service

- Register Salons
- Manage Salon Details
- Search Salons
- View Available Services

### 📅 Booking Service

- Book Appointments
- Cancel Bookings
- Booking History
- Slot Management

### 💳 Payment Service

- Razorpay Integration
- Stripe Integration
- Payment Verification
- Transaction Management

### ☁️ Microservices

- Netflix Eureka
- Spring Cloud Gateway
- Independent Databases
- REST Communication
- Scalable Architecture

---

# 🏗️ System Architecture

```mermaid
graph TD

Client["Client / Frontend"]

Gateway["API Gateway"]

Eureka["Eureka Server"]

User["User Service"]

Salon["Salon Service"]

Booking["Booking Service"]

Category["Category Service"]

Offering["Service Offering"]

Payment["Payment Service"]

Client --> Gateway

Gateway --> User
Gateway --> Salon
Gateway --> Booking
Gateway --> Category
Gateway --> Offering
Gateway --> Payment

User --> Eureka
Salon --> Eureka
Booking --> Eureka
Category --> Eureka
Offering --> Eureka
Payment --> Eureka
Gateway --> Eureka
```

---

# 📦 Microservices

| Service | Port | Description |
|----------|------|-------------|
| Eureka Server | 8070 | Service Discovery |
| Gateway Server | 8087 | API Gateway |
| User Service | - | User Authentication |
| Salon Service | - | Salon Management |
| Booking Service | - | Appointment Booking |
| Category Service | - | Service Categories |
| Service Offering | - | Salon Services |
| Payment Service | - | Razorpay & Stripe |

---

# 🛠 Tech Stack

| Category | Technology |
|------------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.5.16 |
| Cloud | Spring Cloud 2025.0.0 |
| Discovery | Netflix Eureka |
| Gateway | Spring Cloud Gateway |
| Security | Spring Security + JWT |
| Database | MySQL |
| ORM | Spring Data JPA |
| Payment | Razorpay & Stripe |
| Build Tool | Maven |
| Utilities | Lombok |

---

# 📂 Project Structure

```text
Salon-Booking-Project
│
├── booking-service
├── category-service
├── eureka-server
├── gateway-server
├── payment-service
├── salon-service
├── service-offering
└── user-service
```

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/Akashsingh47304/Salon-Booking-project.git

cd Salon-Booking-project
```

---

## Create Databases

```sql
CREATE DATABASE user_service;
CREATE DATABASE salon_service;
CREATE DATABASE booking_service;
CREATE DATABASE category_service;
CREATE DATABASE service_offering;
CREATE DATABASE payment_service;
```

---

## Configure Database

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/database_name
    username: root
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update
```

---

## Start Services

Run services in the following order:

1. Eureka Server
2. Gateway Server
3. User Service
4. Salon Service
5. Category Service
6. Service Offering
7. Booking Service
8. Payment Service

---

# 🌐 API Gateway Routes

Base URL

```
http://localhost:8087
```

| Route | Service |
|--------|----------|
| /api/users/** | User Service |
| /api/salons/** | Salon Service |
| /api/booking/** | Booking Service |
| /api/categories/** | Category Service |
| /api/serviceoffering/** | Service Offering |
| /api/payments/** | Payment Service |

---

# 💳 Payment Flow

```mermaid
sequenceDiagram

User->>Booking Service: Book Appointment

Booking Service->>Payment Service: Create Payment

Payment Service->>Razorpay/Stripe: Generate Payment Link

Razorpay/Stripe-->>Payment Service: Payment Success

Payment Service-->>Booking Service: Confirm Payment

Booking Service-->>User: Booking Confirmed
```

---

# 🔍 Eureka Dashboard

```
http://localhost:8070
```

Use the Eureka Dashboard to monitor all registered microservices.

---

# 📸 Screenshots

- Home Page
- Salon Listing
- Booking Screen
- Payment Screen
- Eureka Dashboard

---

# 🛣 Future Enhancements

- Docker
- OpenFeign
- RabbitMQ
- Redis Caching
- Circuit Breaker
- Keycloak Authentication
- Kubernetes
- CI/CD Pipeline
- AWS Deployment

---

# 🤝 Contributing

1. Fork the repository
2. Create a feature branch

```bash
git checkout -b feature/your-feature
```

3. Commit changes

```bash
git commit -m "Add new feature"
```

4. Push branch

```bash
git push origin feature/your-feature
```

5. Open a Pull Request

---

# 👨‍💻 Author

**Akash Singh**

GitHub: **Akashsingh47304**

---

# ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub.
