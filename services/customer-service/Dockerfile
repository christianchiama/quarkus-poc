###
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14
COPY target/customer-service-*.jar /app.jar
EXPOSE 8090
ENTRYPOINT java -jar /app.jar