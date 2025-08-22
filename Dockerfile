# Use an official Maven image with OpenJDK 21 to build the project
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use an official OpenJDK 21 runtime image
FROM openjdk:21-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/LibraryManagementSystem-1.0-SNAPSHOT.jar app.jar

# Expose the port your app runs on (Change if needed)
EXPOSE 8080

# Run the application
#CMD ["java", "-jar", "app.jar"]
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]

