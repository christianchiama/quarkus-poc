###
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14
COPY target/pet-clinic-service-*.jar /app.jar
EXPOSE 8083
ENV JAVA_OPTS="-Dquarkus.http.host=127.0.0.1 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENTRYPOINT java -jar /app.jar