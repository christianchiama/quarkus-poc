###
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14
COPY target/pet-service-*.jar /app.jar
EXPOSE 8083
ENTRYPOINT java -jar /app.jar