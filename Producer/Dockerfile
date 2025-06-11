# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory inside container (optional but recommended)
WORKDIR /app

# Copy the jar file into the container
COPY target/Producer-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8084

# Run the jar file
CMD ["java", "-jar", "app.jar"]
