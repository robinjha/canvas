FROM maven:3.5-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

#FROM gcr.io/distroless/java
FROM openjdk:alpine
COPY --from=build /usr/src/app/target/frontappserver-1.0-SNAPSHOT.jar /usr/app/frontappserver-1.0-SNAPSHOT.jar
EXPOSE 8124
ENTRYPOINT ["java","-jar","/usr/app/frontappserver-1.0-SNAPSHOT.jar"]