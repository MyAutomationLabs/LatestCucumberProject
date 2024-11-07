FROM maven:3.8-openjdk-11  # Use the latest 3.8.x version with OpenJDK 11
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install
CMD ["mvn", "verify"]
