# Build stage
FROM gradle:jdk21-jammy AS build
WORKDIR /home/gradle/src

# Copy dependency-related files first
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Download dependencies as a separate layer for better caching
RUN gradle dependencies --no-daemon

# Copy source code and build
COPY src ./src
RUN gradle build --no-daemon

# Run stage
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080
ENV PORT 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
