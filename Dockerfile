FROM maven:3.9.4-eclipse-temurin-21-alpine
WORKDIR /app

COPY ./mvnw .

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java","-jar","target/csw-0.0.1.jar"]