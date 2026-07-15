# 💇 Salon Booking Microservices

A scalable **Salon Booking System** built using **Spring Boot Microservices**. The application enables customers to discover salons, book appointments, and manage bookings while demonstrating modern microservice architecture and best practices.

---

## 🚀 Tech Stack

### Backend

* Java 21+
* Spring Boot
* Spring Cloud
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* Maven

### Microservices & Cloud

* Spring Cloud Gateway
* Eureka Service Discovery
* OpenFeign
* Config Server (Optional)
* RabbitMQ / Kafka (Event-driven communication)
* Docker

### Security

* JWT Authentication
* OAuth2 / Keycloak (if implemented)

### Monitoring

* Spring Boot Actuator
* Zipkin
* Grafana
* Prometheus

---

# 🏗️ Microservices

### 👤 User Service

* User Registration
* User Login
* JWT Authentication
* User Profile Management

### 💇 Salon Service

* Register Salon
* Update Salon Details
* View Salon Information
* Search Salons

### 📅 Booking Service

* Create Appointment
* Cancel Booking
* Booking History
* Appointment Status

### ⭐ Review Service *(Optional)*

* Add Review
* Rate Salon
* View Ratings

### 💳 Payment Service *(Optional)*

* Payment Processing
* Payment History

### 🔔 Notification Service *(Optional)*

* Booking Confirmation
* Cancellation Notifications
* Email Notifications

---

# 🏛️ Architecture

```
                Client
                   |
          Spring Cloud Gateway
                   |
        ------------------------
        |         |            |
    User      Salon       Booking
    Service    Service     Service
        |         |            |
        ------------------------
                   |
             Eureka Server
                   |
               RabbitMQ 
                   |
               MySQL Database
```

---

# ✨ Features

* Microservice Architecture
* RESTful APIs
* JWT Authentication
* API Gateway
* Service Discovery
* Inter-Service Communication using OpenFeign
* Event-Driven Communication using RabbitMQ/Kafka
* Global Exception Handling
* Validation
* Logging
* Docker Support
* Scalable and Maintainable Design

---

# 📂 Project Structure

```
Salon-Booking-Microservices
│
├── api-gateway
├── eureka-server
├── user-service
├── salon-service
├── booking-service
├── notification-service
├── payment-service
└── README.md
```

---

# ⚙️ Getting Started

## Clone Repository

```bash
git clone <repository-url>
cd Salon-Booking-Microservices
```

## Start Infrastructure

* Start MySQL
* Start RabbitMQ/Kafka
* Start Eureka Server
* Start API Gateway

## Run Services

Start each microservice individually.

```
user-service
salon-service
booking-service
notification-service
payment-service
```

---

# 🔑 API Examples

## Register User

```
POST /api/users/register
```

## Login

```
POST /api/users/login
```

## Get All Salons

```
GET /api/salons
```

## Create Booking

```
POST /api/bookings
```

## Cancel Booking

```
DELETE /api/bookings/{id}
```

---

# 📌 Future Improvements

* Admin Dashboard
* Recommendation System
* Live Appointment Tracking
* SMS Notifications
* Online Payments
* Docker Compose
* Kubernetes Deployment
* CI/CD Pipeline
* AWS Deployment

---

# 📚 Concepts Demonstrated

* Microservices
* API Gateway
* Service Discovery
* Load Balancing
* JWT Security
* REST APIs
* OpenFeign
* Event-Driven Architecture
* Distributed Tracing
* Centralized Configuration
* Docker
* Clean Architecture

---

# 👨‍💻 Author

**Akash Singh**

Java Backend Developer | Spring Boot | Microservices | REST APIs | MySQL | MongoDB | Docker

---

## ⭐ If you like this project

Give this repository a ⭐ on GitHub and feel free to contribute.
