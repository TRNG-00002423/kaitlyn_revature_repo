# build with maven
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B 

COPY src ./src

RUN mvn package -DskipTests -B 

# runtime with minimal JRE
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -g 1001 -S javalin &&\
    adduser -S javalin -u 1001 -G javalin

COPY --from=build /app/target/javalin-docker-demo-1.0.0.jar app.jar

RUN chown javalin:javalin app.jar

USER javalin

ENV APP_VERSION=1.0.0
ENV PORT=8080

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]