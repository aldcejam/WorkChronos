# Definir a imagem base
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar os arquivos gradle, incluindo gradlew, gradle-wrapper.jar e gradle-wrapper.properties
COPY workchronosAPI/gradle /app/gradle
COPY workchronosAPI/gradlew /app/gradlew
COPY workchronosAPI/build.gradle workchronosAPI/settings.gradle ./

# Copiar o código fonte para o contêiner
COPY workchronosAPI/src /app/src

# Tornar o script gradlew executável
RUN chmod +x gradlew

# Baixar as dependências e construir o projeto com o Gradle Wrapper
RUN ./gradlew build -x test

# Expôr a porta que o Spring Boot usará
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/build/libs/workchronos-api-0.0.1-SNAPSHOT.jar"]
