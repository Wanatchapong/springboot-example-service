# π₯Ύ Spring-Boot Example Service
Minimal example [Spring Boot](http://projects.spring.io/spring-boot/) application

### βοΈ Prerequisites

The followings are needed to build, test and run the project.

- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) or [OpenJDK 11](https://adoptopenjdk.net)
- [Maven 3](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop)

### Dependencies:
- Spring Web πΈοΈ
- Spring Data JPA π
- Spring Validation πͺ
- Spring Boot DevTools βοΈ
- Lombok πΆοΈ
- H2 Database ποΈ
- JUnit 5 π§ͺ
- Mockito πΈ

## π Getting Started

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

## π³ Docker

1. Compile source code and package it in its distribution format as JAR

```
mvn package
```

2. Build docker image and tag the name of image `name:tag`. If not pass a tag, Docker uses "latest" as its default tag.
```
docker build -t springboot-example-service .
```

3. Run docker image in detached mode
```
docker run -d -p 8080:8080 -t springboot-example-service
```

## π Try it out

Open a terminal then make a `GET` request to the server using the `curl` command.

```
curl --request GET \
--url http://localhost:8080/todo \
--header 'content-type: application/json'
```
