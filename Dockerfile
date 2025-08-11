# Stage 1: Build da aplicação usando Maven e JDK 17
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copia os arquivos de configuração do Maven primeiro (para cache de dependências)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Baixa as dependências sem compilar ainda (cache)
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila e empacota o projeto (sem testes)
RUN ./mvnw clean package -DskipTests

# Stage 2: Imagem runtime leve com JDK 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR gerado do estágio anterior
COPY --from=build /app/target/prontuario-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
