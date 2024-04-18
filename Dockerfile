FROM gradle:7.5.1-jdk17 AS build
WORKDIR /home/gradle/src
COPY . .
RUN gradle build --no-daemon

FROM openjdk:17-slim
WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar restaurante-api.jar

ENTRYPOINT ["java", "-jar", "restaurante-api.jar"]