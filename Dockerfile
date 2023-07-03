FROM maven:3.8.3-openjdk-17 as builder
ADD . /src
WORKDIR /src
RUN mvn package -DskipTests
ENTRYPOINT ["java","-Dspring.profiles.active=postgres","-jar","target/lunch-voting-system.jar"]