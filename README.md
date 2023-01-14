# Lunch voting system [![Codacy Badge](https://app.codacy.com/project/badge/Grade/4862a6eb784142bfb8830eab5a5f0e07)](https://www.codacy.com/gh/TheMidgart/lunch-voting-system/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=TheMidgart/lunch-voting-system&amp;utm_campaign=Badge_Grade)

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

## The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote for a restaurant they want to have lunch at today
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

## Running application locally
Lunch voting system is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line (it should work just as well with Java 17 or newer):


```
git clone https://github.com/TheMidgart/lunch-voting-system.git
cd lunch-voting-system
mvn package
java -jar target/*.jar
```
or use
```
mvn spring-boot:run
```

To test api you can use: http://localhost:8080/swagger-ui/index.html
