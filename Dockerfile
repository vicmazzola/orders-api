# ===============================
# Build stage
# ===============================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom first to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build application
COPY src ./src
RUN mvn clean package -DskipTests

# ===============================
# Runtime stage
# ===============================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Create non-root user
RUN useradd -m appuser
USER appuser

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
