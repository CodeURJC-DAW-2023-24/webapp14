#################################################
# Base image for the build container
#################################################
FROM node:20 AS angular_builder

# Set working directory for commands to run
WORKDIR /app

# Copy Angular project files
COPY frontend/webapp14_2/* /app

# Install Angular project dependencies
RUN npm install

COPY frontend/webapp14_2/src /app/src

# Build Angular app
RUN npm run build

#################################################
# Base image for the backend build container
#################################################
FROM --platform=linux/amd64 ubuntu:jammy
FROM maven:3.9.6-amazoncorretto-21 AS builder

# Sets the working directory for commands to run
WORKDIR /project

# Copies the backend project's dependencies
COPY ./pom.xml /project/

# Copies the backend project's code
COPY /src /project/src

COPY --from=angular_builder /app/dist/webapp14_2/browser/ /project/src/main/resources/static/new

# Compiles the backend project
RUN mvn clean package -DskipTests=true

#################################################
# Base image for the application container
#################################################
FROM openjdk:21-jdk-slim AS runtime

WORKDIR /usr/src/app/

# Copy backend JAR
COPY --from=builder /project/target/TicketEvent-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar


# Indicates the port that the container exposes
EXPOSE 8443
