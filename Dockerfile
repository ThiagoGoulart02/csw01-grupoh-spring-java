# Etapa 1: Construção da aplicação
FROM maven:3.9.4-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY pom.xml ./
COPY mvnw ./ 

RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=builder /app/target/csw-0.0.1.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]