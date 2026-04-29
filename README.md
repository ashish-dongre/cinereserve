# CineReserve

A Spring Boot REST API for movie ticket booking system with JWT authentication.

## Features

- **Authentication** - JWT-based authentication with secure login/register
- **Movie Management** - CRUD operations for movies with genre and language categorization
- **Show Scheduling** - Manage screens and show timings
- **Seat Booking** - Reserve and manage show seats with real-time availability
- **Payment Processing** - Handle payment transactions
- **Waitlist** - Automatic seat allocation from waitlist when seats become available
- **Auto Seat Release** - Scheduled task to release unpaid reserved seats

## Tech Stack

- Java 17
- Spring Boot 4.0.6
- Spring Security with JWT
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Project Structure

```
src/main/java/com/cinereserve/
├── controller/          # REST API endpoints
│   ├── AuthController.java
│   ├── MovieController.java
│   ├── ShowController.java
│   ├── BookingController.java
│   ├── PaymentController.java
│   └── WaitlistController.java
├── service/             # Business logic
│   ├── AuthService.java (implicit)
│   ├── MovieService.java
│   ├── ShowService.java
│   ├── BookingService.java
│   ├── PaymentService.java
│   └── WaitlistService.java
├── repository/          # Data access layer
├── entity/              # JPA entities
├── dto/                 # Data transfer objects
├── security/            # JWT & security configuration
├── scheduler/           # Scheduled tasks
├── exception/           # Custom exceptions & handlers
└── enums/               # Enumeration types
```

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | User login |

### Movies
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/movies` | Get all movies |
| GET | `/api/movies/{id}` | Get movie by ID |
| POST | `/api/movies` | Create movie (admin) |
| PUT | `/api/movies/{id}` | Update movie |
| DELETE | `/api/movies/{id}` | Delete movie |

### Shows
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/shows` | Get all shows |
| GET | `/api/shows/{id}` | Get show by ID |
| GET | `/api/shows/movie/{movieId}` | Get shows by movie |
| POST | `/api/shows` | Create show |
| DELETE | `/api/shows/{id}` | Delete show |

### Booking
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/bookings` | Create booking |
| GET | `/api/bookings/{id}` | Get booking by ID |
| GET | `/api/bookings/user/{userId}` | Get user bookings |
| PUT | `/api/bookings/{id}/cancel` | Cancel booking |

### Payments
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/payments` | Process payment |
| GET | `/api/payments/{id}` | Get payment details |

### Waitlist
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/waitlist` | Join waitlist |
| GET | `/api/waitlist/{id}` | Get waitlist entry |
| DELETE | `/api/waitlist/{id}` | Leave waitlist |

## Configuration

Update `src/main/resources/application.properties` with your database settings:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cinereserve
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### JWT Configuration
```properties
jwt.secret=your-secret-key-minimum-256-bits
jwt.expiration=86400000
```

## Getting Started

### Prerequisites
- JDK 17+
- Maven 3.6+
- PostgreSQL 13+

### Build & Run
```bash
# Clone the repository
git clone https://github.com/miashu07/cinereserve.git
cd cinereserve

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Run Tests
```bash
mvn test
```

## Database Schema

- **User** - User accounts with authentication
- **Movie** - Movie information (title, genre, language, duration, poster)
- **Screen** - Theatre screens with seat capacity
- **Show** - Show timings linking movies to screens
- **ShowSeat** - Individual seats for each show with status
- **Booking** - User bookings with payment status
- **BookingSeat** - Junction table for booking-seat relationship
- **Payment** - Payment transaction records
- **Waitlist** - Waitlist entries for full shows

## License

This project is licensed under the MIT License.