# Build stage
FROM gradle:jdk21-jammy AS build
WORKDIR /home/gradle/src
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
# Download dependencies first (better caching)
RUN gradle dependencies --no-daemon

# Then copy source code and build
COPY src ./src
RUN gradle build --no-daemon --info

# Run stage
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
