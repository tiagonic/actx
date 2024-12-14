# Use uma imagem base oficial do Maven para construção e Spring Boot para execução
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Agora crie uma imagem menor, copiando o artefato construído
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
