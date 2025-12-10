# 🛒 Orders API

A clean and modular Spring Boot API designed to practice **real-world backend development**,  
**domain-driven design**, **validation**, and **clean architecture**.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.4.12**
- **Maven**
- **Jakarta Validation**
- **BigDecimal** for financial accuracy

---

## 📦 Domain Model (Work in Progress)

### ✔ Product

- Mandatory fields with validation (id, name, price)
- Defensive constructors to prevent invalid state

### ✔ OrderItem

- Holds product + quantity + discount policy
- Calculates total using **BigDecimal**
- Supports custom discount strategies (e.g. `NoDiscount`, `PercentageDiscount` – coming soon)

### ✔ Order (Aggregate Root)

- Holds multiple OrderItems
- Computes full order total
- New constructor added to support item list initialization

---

## 🌐 API Features (In Progress)

- `POST /orders` – create an order from DTOs
- `GET /orders/{id}` – retrieve stored orders
- Basic error handling (404 for non-existent orders)
- DTO → Domain mapping inside the Service layer

Upcoming:

- Full persistence using Spring Data JPA + H2
- Product repository and validation
- Global exception handling
- Advanced discount policies

---

## ▶️ How to Run

```bash
./mvnw spring-boot:run
