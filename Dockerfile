FROM maven:3.8.6-openjdk-11
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install
CMD ["mvn", "verify"]
