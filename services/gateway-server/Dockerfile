FROM openjdk:17-jdk-alpine
COPY target/gateway-server*.jar ms-gateway.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "ms-gateway.jar"]
RUN apk add --no-cache curl