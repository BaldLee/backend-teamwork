FROM openjdk:8-jdk-alpine
MAINTAINER YuxuanLiu
EXPOSE 8081
COPY ./target/spring-security-auth-server-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT [ "java","-jar", "/app.jar" ]