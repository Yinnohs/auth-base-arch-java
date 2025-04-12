# JWT Authentication & Authorization System

A robust and scalable authentication and authorization system built with Spring Boot, implementing JWT (JSON Web Tokens) using a modular monolith approach with Hexagonal Architecture and Screaming Architecture principles.

## 🏗️ Architecture

This project follows a modular monolith approach with:

- **Hexagonal Architecture (Ports & Adapters)**: Clear separation between domain logic and external concerns
- **Screaming Architecture**: Package structure that screams its purpose
- **Domain-Driven Design (DDD)**: Focus on domain models and business logic

### Project Structure

```
src/main/java/com/yinnohs/security/jwt/
├── auth/                    # Authentication module
│   ├── application/        # Application services and use cases
│   ├── domain/            # Domain models and business logic
│   └── infrastructure/    # Infrastructure implementations
├── user/                   # User management module
├── global/                 # Shared components
└── JwtApplication.java    # Application entry point
```

## 🚀 Features

- JWT-based authentication
- Role-based authorization
- RSA key pair for JWT signing
- Modular and maintainable codebase
- Clean separation of concerns
- Domain-driven design principles
- Infrastructure-agnostic domain layer

## 🛠️ Technical Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- RSA Key Pair for token signing
- Maven for dependency management

## 📦 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Your favorite IDE (IntelliJ IDEA recommended)

## 🔧 Installation

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Navigate to the project directory:
```bash
cd jwt
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## 🔐 Security Configuration

The application uses RSA key pairs for JWT signing. Configure your keys in the application properties:

```properties
# RSA Key Configuration
jwt.private-key=your-private-key
jwt.public-key=your-public-key
```

## 🏗️ Project Structure

### Domain Layer
- Contains business logic and domain models
- Independent of infrastructure concerns
- Defines core business rules

### Application Layer
- Implements use cases
- Orchestrates domain objects
- Defines ports (interfaces)

### Infrastructure Layer
- Implements ports defined in the application layer
- Handles external concerns (database, HTTP, etc.)
- Contains adapters for external systems

## 🔍 Testing

Run the test suite:
```bash
mvn test
```

## 📝 API Documentation

The API documentation is available at:
```
http://localhost:8080/swagger-ui.html
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- Your Name - Initial work

## 🙏 Acknowledgments

- Spring Security team
- JWT community
- Clean Architecture principles 