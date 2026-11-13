FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/target/bff-schedule-tasks-0.0.1-SNAPSHOT.jar /app/bff-schedule-tasks.jar

EXPOSE 8084

CMD ["java", "-jar", "/app/bff-schedule-tasks.jar"]