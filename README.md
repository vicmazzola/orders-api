# 🛒 Orders API

A clean and modular Spring Boot API designed to practice **real-world backend development**,  
**domain-driven design**, **validation**, and **clean architecture**.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.4.12**
- **Spring Data JPA**
- **H2 Database**
- **Jakarta Validation**
- **Maven**
- **BigDecimal** for financial accuracy

---

## 📦 Domain Model

### ✔ Product
- Persistent entity with id, name, and price
- Used as a mandatory reference in orders

### ✔ OrderItem
- Represents a product and its quantity
- Part of the `Order` aggregate
- Calculates totals using **BigDecimal**
- Supports discount policies (e.g. `NoDiscount`)

### ✔ Order (Aggregate Root)
- Owns and manages multiple `OrderItem`s
- Controls item lifecycle via cascade + orphan removal
- Computes full order total
- Enforces aggregate consistency

---

## ▶️ How to Run

```bash
./mvnw spring-boot:run
```

### H2 Console:
http://localhost:8080/h2-console
