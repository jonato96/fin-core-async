FROM openjdk:17-jdk-alpine
COPY target/customer-service*.jar ms-customer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ms-customer.jar"]
RUN apk add --no-cache curl