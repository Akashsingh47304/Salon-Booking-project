💇 Salon Booking Microservices Platform
<p align="center"> <img src="<img width="1536" height="1024" alt="ChatGPT Image Jul 17, 2026, 12_28_59 PM" src="https://github.com/user-attachments/assets/a962bd4c-df38-4091-b9e4-ad388ce122e7" />
" width="100%" alt="Salon Booking Banner"> </p> <p align="center">










</p>
📖 Overview

Salon Booking is a Spring Boot Microservices application that enables users to discover salons, browse available services, schedule appointments, and securely complete payments.

The project demonstrates a production-style distributed system using Spring Cloud, Netflix Eureka, API Gateway, JWT Authentication, and separate databases for each microservice.

✨ Features
👤 User Management
User Registration
Secure Login
JWT Authentication
Role Based Authorization
💇 Salon Management
Register Salons
Update Salon Details
Search Salons
Browse Available Services
📅 Booking
Book Appointments
Cancel Bookings
View Booking History
Slot Management
💳 Payments
Razorpay Integration
Stripe Integration
Payment Verification
Transaction Tracking
☁️ Microservices
Eureka Service Discovery
Spring Cloud Gateway
Independent Databases
REST Communication
Fault Isolation
🏗 System Architecture
                           Client
                              │
                              ▼
                    Spring Cloud Gateway
                              │
               ───────── Eureka Server ─────────
                              │
      ┌──────────┬──────────┬──────────┬──────────┐
      ▼          ▼          ▼          ▼          ▼
 User Service  Salon     Booking   Category   Payment
               Service    Service    Service    Service
                              │
                              ▼
                     Service Offering
📦 Microservices
Service	Description
Eureka Server	Service Discovery
Gateway Server	API Gateway
User Service	Authentication & User Management
Salon Service	Salon Profiles
Booking Service	Appointment Booking
Category Service	Categories
Service Offering	Salon Services
Payment Service	Razorpay & Stripe
⚙ Tech Stack
Category	Technology
Language	Java 21
Backend	Spring Boot 3.5
Cloud	Spring Cloud
Discovery	Netflix Eureka
Gateway	Spring Cloud Gateway
Security	Spring Security + JWT
Database	MySQL
ORM	Spring Data JPA
Payment	Razorpay & Stripe
Build Tool	Maven
Utilities	Lombok
📂 Project Structure
Salon-Booking-Project

├── eureka-server
├── gateway-server
├── user-service
├── salon-service
├── booking-service
├── category-service
├── service-offering
└── payment-service
🚀 Quick Start
Clone
git clone https://github.com/Akashsingh47304/Salon-Booking-project.git

cd Salon-Booking-project
Create Databases
CREATE DATABASE user_service;
CREATE DATABASE salon_service;
CREATE DATABASE booking_service;
CREATE DATABASE category_service;
CREATE DATABASE service_offering;
CREATE DATABASE payment_service;
Configure MySQL
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/database_name
    username: root
    password: ****
Start Services

Run in this order:

Eureka Server
Gateway Server
User Service
Salon Service
Category Service
Service Offering
Booking Service
Payment Service
🌐 API Gateway

Base URL

http://localhost:8087
Endpoint	Service
/api/users/**	User
/api/salons/**	Salon
/api/booking/**	Booking
/api/categories/**	Category
/api/serviceoffering/**	Service Offering
/api/payments/**	Payment
💳 Payment Flow
User
   │
   ▼
Booking Service
   │
   ▼
Payment Service
   │
   ├── Razorpay
   └── Stripe
   │
   ▼
Payment Verification
🔍 Eureka Dashboard
http://localhost:8070

View all registered microservices from the Eureka Dashboard.

📸 Screenshots
Home Page

Salon Listing

Booking Screen

Payment Screen

Eureka Dashboard
🛣 Roadmap
Docker Support
Keycloak Authentication
RabbitMQ Event Communication
OpenFeign Communication
Redis Caching
Circuit Breaker
Kubernetes Deployment
CI/CD Pipeline
AWS Deployment
🤝 Contributing

Contributions are welcome.

Fork the repository
Create a new branch
Commit your changes
Push your branch
Open a Pull Request
👨‍💻 Author

Akash Singh

GitHub: Akashsingh47304

⭐ If you found this project useful, don't forget to star the repository!
