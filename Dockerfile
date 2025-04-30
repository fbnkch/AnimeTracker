
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar


EXPOSE 8080

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "app.jar"]
