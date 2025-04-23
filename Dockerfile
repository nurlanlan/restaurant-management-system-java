# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/restaurant-management-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "app.jar"]