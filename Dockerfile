#################################################
# Base image for the build container
#################################################
FROM node:20 AS angular_builder

# Set working directory for commands to run
WORKDIR /app

# Copy Angular project files
COPY frontend/webapp14_2/package.json frontend/package-lock.json /app/
COPY frontend /app

# Install Angular project dependencies
RUN npm install

# Build Angular app
RUN npm run build

#################################################
# Base image for the backend build container
#################################################
FROM maven:3.9.6-openjdk-16 AS backend_builder

# Sets the working directory for commands to run
WORKDIR /project

# Copies the backend project's dependencies
COPY backend/pom.xml /project/

# Copies the backend project's code
COPY backend/src /project/src

# Compiles the backend project
RUN mvn clean package -DskipTests=true

#################################################
# Base image for the application container
#################################################
FROM openjdk:16-jdk-slim AS runtime

WORKDIR /usr/src/app/

# Copy backend JAR
COPY --from=backend_builder /project/target/TicketEvent-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar

# Copy frontend build
COPY --from=angular_builder /app/dist/frontend /usr/src/app/frontend

# Indicates the port that the container exposes
EXPOSE 8443

# Command to run on docker run
CMD ["java", "-jar", "app.jar"]
