# Fleet Management Application

A basic fleet management application that is designed according to the given [README.md](PROJECT_README.md) file.

## Design Decisions
- I followed general patterns for basic Spring boot applications. JPA repositories are used to handle database queries
and the connection because I wanted to keep the code as simple as I can. I thought adding extra sqls and using other 
dependencies such as JDBC may bring some extra complexities. Also, I used PostgreSQL but any relational database can 
be used in the design.
- Also, I used some design patterns, such as;
- To make it open to change for further needs by using Strategy Desing Pattern
- To keep it simple by using Template Design Pattern .

### General Notes
- I couldn't write any comments because I think the code should be simple and self-descriptive with giving good names 
  and coding a readable code blocks.
- There are Postman tests to make sure that the code is working as expected in end-to-end approach.

### Used Technologies
- Spring boot
- Lombok
- Mapstruct
- Java 11
- PostgreSQL
- Spring Data JPA

## How to run
First, you have to run the command bellow
```
docker-compose up
```
Then, on a seperate terminal ,you should run the following commands one-by-one.
```
mvn clean instal
mvn spring-boot:run
```
Also, there is a docker file which you can use it in a docker enviroment using the commands bellow(not tested).
```
docker build --tag=fleet-management-app:v0.1 .
docker run -p8080:8080 fleet-management-app:v0.1
```