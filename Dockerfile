# Etapa 1: build con Gradle
FROM gradle:8.5.1-jdk17-alpine AS builder

WORKDIR /app

# copiamos todo el microservicio
COPY . .

# 🔴 IMPORTANTE: dar permiso de ejecución al gradlew dentro del contenedor
RUN chmod +x ./gradlew

# construimos el jar (saltando tests)
RUN ./gradlew bootJar -x test --no-daemon

# Etapa 2: imagen liviana solo con el JRE y el jar
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# copiamos el jar generado desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# puerto interno del microservicio (8081 en tu application.properties)
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
