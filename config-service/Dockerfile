FROM openjdk:11
COPY ./target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
ENV GIT_USERNAME=aldanedev
ENV GIT_PASSWORD=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
EXPOSE 8081
ENTRYPOINT ["java","-jar","config-service.jar"]