# 🛒 Orders API

A clean and modular Spring Boot API designed to practice **real-world backend development**,  
**domain-driven design**, **validation**, and **clean architecture**.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.4.12**
- **Spring Data JPA**
- **PostgreSQL** (development)
- **H2 Database** (tests)
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

## 🔒 Transactions & Persistence

Transactional boundaries are defined at the service layer.

- Write operations (`create`, `update`, `delete`) are wrapped in `@Transactional`
  to ensure atomic changes to the Order aggregate.
- Read operations that traverse lazy-loaded relationships use
  `@Transactional(readOnly = true)` to keep the persistence context open safely.
- Controllers and repositories do not manage transactions directly.

This keeps transaction scope aligned with business use cases.


---

## 🧪 Testing

The project follows a layered testing approach to validate behavior at different levels:

- **Domain tests (JUnit 5)**  
  Verify order calculations and discount rules with no Spring context.

- **Service tests (Mockito)**  
  Validate core use cases and exception paths with mocked repositories.

- **Integration tests (SpringBootTest)**  
  Minimal end-to-end verification using a real Spring context and H2:
    - `POST /orders` → 201 CREATED
    - `GET /orders/{id}` → returns persisted order

The focus is on **confidence over coverage**, avoiding redundant or brittle tests.


---

## ▶️ How to Run

```bash
./mvnw spring-boot:run
```

### H2 Console:
http://localhost:8080/h2-console
