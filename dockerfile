FROM openjdk:17-jdk-slim

WORKDIR /app

COPY workchronosAPI/gradle /app/gradle
COPY workchronosAPI/gradlew /app/gradlew
COPY workchronosAPI/build.gradle workchronosAPI/settings.gradle ./

COPY workchronosAPI/src /app/src

RUN chmod +x gradlew

RUN ./gradlew build -x test

EXPOSE 8080

CMD ["java", "-jar", "/app/build/libs/workchronos-api-0.0.1-SNAPSHOT.jar"]
