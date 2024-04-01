
#################################################
# Base image for the build container
#################################################
FROM --platform=linux/amd64 ubuntu:jammy
FROM  maven:3.9.6-eclipse-temurin-21 as builder

ARG  POM_FILE
ARG JAR_FILE
ARG SRC_DIR



# Sets the working directory for commands to run
WORKDIR /app

# Copies the project's dependencies
COPY $POM_FILE ./pom.xml

# Downloads the project's dependencies
#RUN mvn clean verify

# Copies the project's code
COPY $SRC_DIR ./src

# Compiles the project
RUN mvn clean package -DskipTests=true



#################################################
# Base image for the application container
#################################################
FROM openjdk:21-jdk-slim AS runtime

ARG POM_FILE
ARG JAR_FILE
ARG SRC_DIR



WORKDIR /app

COPY  --from=builder $JAR_FILE app.jar





# Indicates the port that the container exposes
EXPOSE 8443

# Command to run on docker run
CMD ["java", "-jar", "app.jar"]
