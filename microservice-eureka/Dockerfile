# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Copia el archivo JAR generado por Maven o Gradle
COPY target/microservice-eureka.jar app.jar

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]