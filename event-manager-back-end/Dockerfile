# Etapa 1: Build da aplicação
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app

# Copia o projeto inteiro
COPY . .

# Executa o build com o Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Etapa 2: Executar o JAR gerado
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o .jar do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
