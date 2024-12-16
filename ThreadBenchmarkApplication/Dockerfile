# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the packaged application
COPY ./target/ThreadBenchmarkApplication.jar /app

# Run the application
ENTRYPOINT ["java", "-jar", "ThreadBenchmarkApplication.jar"]