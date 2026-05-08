# -------- BUILD STAGE --------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Dar permisos al wrapper
RUN chmod +x gradlew

# Compilar usando el wrapper (SIN tests)
RUN ./gradlew clean bootJar -x test --no-daemon

# -------- RUN STAGE ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
``
