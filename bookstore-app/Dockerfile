FROM openjdk:11-jdk-slim
VOLUME /tpm
ADD target/bookstore-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./unrandom", "-jar", "/app.jar" ]