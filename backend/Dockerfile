# Docker multi-stage build
FROM maven:3-jdk-8

ADD . /testyourselfe
WORKDIR /testyourselfe

# Just echo so we can see, if everything is there :)
RUN ls -l

RUN mvn clean install
# Run Maven build

FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]