###
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14
COPY target/treatment-service-*.jar /app.jar
EXPOSE 8088
ENTRYPOINT java -jar -Dquarkus.http.port=8088 /app.jar