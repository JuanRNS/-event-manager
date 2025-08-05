# Usa imagem com Java (Temurin é mantido pela Eclipse Foundation)
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o .jar gerado pelo Maven para dentro do container
COPY target/*.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8080

# Comando que roda sua aplicação Spring
ENTRYPOINT ["java", "-jar", "app.jar"]
