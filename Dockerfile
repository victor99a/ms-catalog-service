# Etapa 1: construir el JAR con Gradle
FROM gradle:8.5-jdk17-alpine AS build

WORKDIR /app

# Copiamos TODO el proyecto (el módulo catalog-service)
COPY . .

# Construimos el jar de Spring Boot (saltando los tests)
RUN ./gradlew bootJar -x test --no-daemon

# Etapa 2: imagen liviana solo con el JRE y el jar
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia el jar generado en la etapa de build
COPY --from=build /app/build/libs/*SNAPSHOT*.jar app.jar

# El puerto interno de la app (Spring usará server.port=${PORT:8081})
EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
