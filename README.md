
![Uploading ChatGPT Image Jul 17, 2026, 12_18_56 PM.png…]()

Salon Booking Microservices Application
A comprehensive microservices-based salon booking platform built with Spring Boot, Spring Cloud, and Java 21. The system enables users to discover salons, book appointments, and process payments through a distributed architecture.

🏗️ Architecture
This project follows a microservices architecture with the following components:



┌─────────────┐      ┌─────────────┐      ┌─────────────┐
│   Client    │──────│   Gateway   │──────│   Eureka    │
└─────────────┘      └─────────────┘      └─────────────┘
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
┌───────▼──────┐   ┌──────▼──────┐   ┌──────▼──────┐
│ User Service │   │Salon Service│   │Booking Svc  │
└──────────────┘   └─────────────┘   └─────────────┘
        │                  │                  │
┌───────▼──────┐   ┌──────▼──────┐   ┌──────▼──────┐
│Category Svc  │   │Service Off. │   │Payment Svc  │
└──────────────┘   └─────────────┘   └─────────────┘
📦 Services
Service	Port	Description
eureka-server	8070	Service Discovery Server
gateway-server	8087	API Gateway with routing
user-service	-	User management & authentication
salon-service	-	Salon profile management
booking-service	-	Appointment booking system
category-service	-	Service categories
service-offering	-	Service offerings management
payment-service	-	Payment processing (Razorpay & Stripe)
🛠️ Tech Stack
Java 21 - Programming language
Spring Boot 3.5.16 - Application framework
Spring Cloud 2025.0.0 - Microservices framework
Spring Cloud Gateway - Reactive API Gateway
Netflix Eureka - Service Discovery
Spring Data JPA - Database abstraction
MySQL - Relational database
Lombok - Code generation
Razorpay Java SDK 1.4.8 - Payment integration
Stripe Java SDK 30.0.0 - Payment integration
Maven - Build tool
📋 Prerequisites
Java 21 or higher
Maven 3.6+
MySQL 8.0+
IDE (IntelliJ IDEA, Eclipse, or VS Code)
🚀 Getting Started
1. Clone the Repository


bash
git clone https://github.com/Akashsingh47304/Salon-Booking-project.git
cd Salon-Booking-project
2. Database Setup
Create MySQL databases for each service:



sql
CREATE DATABASE user_service;
CREATE DATABASE salon_service;
CREATE DATABASE booking_service;
CREATE DATABASE category_service;
CREATE DATABASE service_offering;
CREATE DATABASE payment_service;
3. Configure Application Properties
Update the application.yml files in each service with your MySQL credentials:



yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/<database_name>
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
4. Build the Project
Navigate to each service directory and build:



bash
cd eureka-server
mvn clean install

cd ../gateway-server
mvn clean install

# Repeat for all services
5. Start Services in Order
Eureka Server (Port 8070)


bash
cd eureka-server
mvn spring-boot:run
Gateway Server (Port 8087)


bash
cd gateway-server
mvn spring-boot:run
User Service


bash
cd user-service
mvn spring-boot:run
Salon Service


bash
cd salon-service
mvn spring-boot:run
Category Service


bash
cd category-service
mvn spring-boot:run
Service Offering


bash
cd service-offering
mvn spring-boot:run
Booking Service


bash
cd booking-service
mvn spring-boot:run
Payment Service


bash
cd payment-service
mvn spring-boot:run
🌐 API Gateway Routes
All requests are routed through the API Gateway at http://localhost:8087

Path Pattern	Target Service
/api/users/**, /auth/**, /users/**	user-service
/api/salons/**, /salons/**	salon-service
/api/booking/**	booking-service
/api/serviceoffering/**	service-offering
/api/payments/**	payment-service
/api/categories/**	category-service
🔧 Configuration
Eureka Dashboard
Access the Eureka Server dashboard at: http://localhost:8070

Service Registration
All services are configured to register with Eureka at:



yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8070/eureka/
💳 Payment Integration
The payment service supports:

Razorpay - Indian payment gateway
Stripe - Global payment platform
Configure your API keys in the payment service application.yml:



yaml
payment:
  razorpay:
    key-id: your_razorpay_key_id
    key-secret: your_razorpay_key_secret
  stripe:
    secret-key: your_stripe_secret_key
📁 Project Structure


Salon_project/
├── booking-service/          # Booking management
├── category-service/        # Service categories
├── eureka-server/           # Service discovery
├── gateway-server/          # API gateway
├── payment-service/         # Payment processing
├── salon-service/           # Salon profiles
├── service-offering/        # Service offerings
└── user-service/            # User management
🔍 Service Details
User Service
User registration and authentication
Profile management
Role-based access control
Salon Service
Salon registration and management
Salon search and discovery
Location-based services
Booking Service
Appointment scheduling
Booking management
Availability tracking
Category Service
Service category management
Category hierarchy
Service Offering
Service catalog management
Pricing and duration
Service-salon mapping
Payment Service
Payment processing
Transaction management
Refund handling
🧪 Testing
Run tests for individual services:



bash
cd <service-name>
mvn test
🐛 Troubleshooting
Services not registering with Eureka
Ensure Eureka server is running first
Check application.yml for correct Eureka URL
Verify network connectivity
Database connection errors
Confirm MySQL is running
Check database credentials in application.yml
Ensure databases are created
Gateway routing issues
Verify target services are registered in Eureka
Check route configurations in gateway-server/application.yml
Ensure service names match exactly
📝 Environment Variables
Consider using environment variables for sensitive data:



bash
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export RAZORPAY_KEY_ID=your_key_id
export RAZORPAY_KEY_SECRET=your_key_secret
export STRIPE_SECRET_KEY=your_secret_key
🤝 Contributing
Fork the repository
Create your feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request

👨‍💻 Author
Akash Singh
