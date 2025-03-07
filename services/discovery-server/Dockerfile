FROM openjdk:17-jdk-alpine
COPY target/discovery-server*.jar ms-discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "ms-discovery.jar"]
RUN apk add --no-cache curl