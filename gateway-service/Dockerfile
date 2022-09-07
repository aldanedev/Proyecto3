FROM openjdk:11
COPY ./target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
ENV CONFIG_SERVICE_URL=""
ENV REGISTRY_SERVICE_URL=""
EXPOSE 8080
ENTRYPOINT ["java","-jar","gateway-service.jar"]