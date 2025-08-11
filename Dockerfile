# Imagem oficial leve do OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR do projeto para dentro do container
COPY target/*.jar app.jar

# Expõe a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "prontuario-0.0.1-SNAPSHOT.jar"]
