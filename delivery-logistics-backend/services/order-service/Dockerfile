# Imagem base ultra-leve com Java (Alpine Linux)
FROM eclipse-temurin:21-jdk-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o pacote gerado pelo Maven para o container
COPY target/*.jar app.jar

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]