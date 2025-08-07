# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar archivos de configuración primero para aprovechar el cache
COPY pom.xml .

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Copiar el código fuente
COPY src ./src

# Construir el JAR del proyecto
RUN ./mvnw clean package -DskipTests

# Stage 2: Crear una imagen ligera para ejecución
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=build /app/target/ms-inventario-0.0.1-SNAPSHOT.jar app.jar

# Crear directorio para configuraciones
RUN mkdir -p /app/config

# Exponer el puerto en el que tu app escucha
EXPOSE 8082

# Variables de entorno por defecto
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando de ejecución
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.config.additional-location=file:/app/config/ -jar app.jar"]
