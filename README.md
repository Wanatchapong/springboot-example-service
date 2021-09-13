# Spring-Boot Example Service
Minimal example [Spring Boot](http://projects.spring.io/spring-boot/) application

### Prerequisites

The followings are needed to build, test and run the project.

- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Maven 3](https://maven.apache.org/download.cgi)

### Dependencies:
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Boot DevTools
- Lombok
- H2 Database
- JUnit 5
- Mockito

## Getting Started

Follow the below instructions to build, test and run the project on your local machine.

## Clone

Get started by cloning this repository using git command.


```
git clone git@github.com:Wanatchapong/springboot-example-service.git
```

## Compile

And compile the project using the following maven command.

```
mvn compile 
```

## Build jar file

And build the project jar file using the following maven command.

```
mvn package 
```

## Test

Unit test project using the following maven command.


```
mvn test
```


## Run

Run project using the following maven command.


```
mvn spring-boot:run
```
or run from the jar file

```
java -jar target/<projectname-version>.jar 
```