#FROM ubuntu:latest
#
#MAINTAINER Yanuo Ma "myn508@hotmail.com"
#
#ENV version = docker
#ENV dbuser
#ENV dbpass
#ENV jdbcurl
#
#RUN apt-get update && apt-get install -y openjdk-8-jdk
#
#WORKDIR /usr/local/bin
#
#ADD target/project-management-0.0.1-SNAPSHOT.jar .
#
#ENTRYPOINT ["java", "-jar", "project-management-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8-jdk-alpine
ENV version docker_build
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]