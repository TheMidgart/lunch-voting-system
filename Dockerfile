FROM maven:3.8.3-openjdk-17 as builder
COPY . /src
WORKDIR /src
RUN mvn package
ENTRYPOINT ["java","-jar","target/lunch-voting-system.jar"]