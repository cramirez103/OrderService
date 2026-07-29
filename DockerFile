# Use official OpenJDK runtime image as base
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/orderservice-1.0-SNAPSHOT.jar app.jar

# Expose port (if applicable)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
