# =========================================================================
# 1. BUILDER STAGE
# =========================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . /app

RUN mvn clean install -DskipTests

RUN echo "=== Listing all directories ===" && \
    ls -la /app/ && \
    echo "=== Looking for jar files ===" && \
    find /app -name "*.jar" -type f && \
    echo "=== Bookservice directory ===" && \
    ls -la /app/bookservice/ || echo "No bookservice dir" && \
    echo "=== Notification-service directory ===" && \
    ls -la /app/notification-service/ || echo "No notification-service dir"

# =========================================================================
# 2. BOOKSERVICE FINAL STAGE
# =========================================================================
FROM eclipse-temurin:17-jre-focal AS bookservice-final
WORKDIR /app
COPY --from=builder /app/bookservice/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# =========================================================================
# 3. NOTIFICATION-SERVICE FINAL STAGE
# =========================================================================
FROM eclipse-temurin:17-jre-focal AS notification-service-final
WORKDIR /app
COPY --from=builder /app/notification-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]