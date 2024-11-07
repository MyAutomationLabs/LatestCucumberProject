# Dockerfile
FROM maven:3.8-openjdk-11  # Ensure no extra spaces here
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install  # Install dependencies
CMD ["mvn", "verify"]  # Command to run tests
