# Use a base image with Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /bank_finance_app

# Copy the jar file to the container
COPY target/account-microservice-0.0.1-SNAPSHOT.jar bank_finance_app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "bank_finance_app.jar"]
