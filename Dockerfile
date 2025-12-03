# Etapa 1: build con JDK 17 y Gradle Wrapper
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Hacemos ejecutable el gradlew (por si acaso)
RUN chmod +x ./gradlew

# Construimos el JAR (sin tests)
RUN ./gradlew bootJar -x test --no-daemon

# Etapa 2: imagen liviana sólo con el JRE y el jar
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el jar creado en la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# El puerto lo inyecta Railway con la variable PORT
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
