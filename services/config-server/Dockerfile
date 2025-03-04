FROM openjdk:17-jdk-alpine
COPY target/config-server*.jar ms-config.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "ms-config.jar"]
RUN apk add --no-cache curl