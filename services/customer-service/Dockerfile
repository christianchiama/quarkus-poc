###
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14
COPY target/customer-service-*.jar /app.jar
ENTRYPOINT java -jar /app.jar