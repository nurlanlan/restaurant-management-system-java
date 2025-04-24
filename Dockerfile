## Stage 1: Build the application
#FROM maven:3.8.6-eclipse-temurin-17 AS builder
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package
#
## Stage 2: Run the application
#FROM eclipse-temurin:17-jre
#WORKDIR /app
#COPY --from=builder /app/target/restaurant-management-0.0.1-SNAPSHOT.jar ./app.jar
#EXPOSE 9092
#CMD ["java", "-jar", "app.jar"]
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY restaurant-management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9092
CMD ["java", "-jar", "app.jar"]

