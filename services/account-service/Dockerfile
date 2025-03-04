FROM openjdk:17-jdk-alpine
COPY target/account-service*.jar ms-account.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "ms-account.jar"]
RUN apk add --no-cache curl