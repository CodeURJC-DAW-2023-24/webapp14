
#################################################
# Base image for the build container
#################################################
FROM --platform=linux/amd64 ubuntu:jammy
FROM maven:3.9.6-amazoncorretto-21 as builder


# Sets the working directory for commands to run
WORKDIR /project

# Copies the project's dependencies
COPY ../pom.xml /project/

# Downloads the project's dependencies
#RUN mvn clean verify

# Copies the project's code
COPY /src /project/src

# Compiles the project
RUN mvn clean package -DskipTests=true



#################################################
# Base image for the application container
#################################################
FROM openjdk:21-jdk-slim AS runtime

WORKDIR /usr/src/app/





COPY  --from=builder /project/target/TicketEvent-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar





# Indicates the port that the container exposes
EXPOSE 8443

# Command to run on docker run
CMD ["java", "-jar", "app.jar"]
