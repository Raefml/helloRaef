


FROM openjdk:17-jdk-alpine


WORKDIR /app


COPY target/helloRaef-0.0.1-SNAPSHOT.jar /app/helloRaef.jar


EXPOSE 8081

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/helloRaef.jar"]
