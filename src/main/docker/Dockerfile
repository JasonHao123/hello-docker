FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080/tcp
ADD hello-docker.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]