# Docker multi-stage build
FROM maven:3-jdk-8

ADD . /testyourselfe
WORKDIR /testyourselfe

# Just echo so we can see, if everything is there :)
RUN ls -l

RUN mvn clean install

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8098
ENTRYPOINT ["java","-jar","/app.jar"]