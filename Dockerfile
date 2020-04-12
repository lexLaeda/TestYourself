# Docker multi-stage build

# 1. Building the App with Maven
FROM maven:3-jdk-8

ADD . /testyourself
WORKDIR /testyourself

# Just echo so we can see, if everything is there :)
RUN ls -l

# Run Maven build
RUN mvn clean install


RUN mvn --projects backend spring-boot:run