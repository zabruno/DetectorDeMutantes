# ========================================
# ETAPA 1: BUILD
# ========================================
FROM gradle:8.5-jdk21-alpine AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew bootJar --no-daemon

# ========================================
# ETAPA 2: RUNTIME
# ========================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/build/libs/GlobalMutantsDetector.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
