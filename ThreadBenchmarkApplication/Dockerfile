# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the packaged application
COPY ./target/ThreadBenchmarkApplication-0.0.1.jar /app

# Run the application
ENTRYPOINT ["java", "-jar", "ThreadBenchmarkApplication-0.0.1.jar"]