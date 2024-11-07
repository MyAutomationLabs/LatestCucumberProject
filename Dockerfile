# Dockerfile
FROM maven:3.8.7-openjdk-11
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install  # Install dependencies
CMD ["mvn", "verify"]  # Command to run tests
