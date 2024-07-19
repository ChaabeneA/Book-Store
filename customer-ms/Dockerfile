# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17
VOLUME /tmp
WORKDIR /app
COPY --from=build /app/target/customer-ms-0.0.1-SNAPSHOT.jar customer-ms.jar
COPY src src
COPY pom.xml pom.xml
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS JAVA_OPTS=""
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/customer-ms.jar"]
