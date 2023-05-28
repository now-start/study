# study
This project is designed to study Spring Boot 3.X, CI/CD, and various architectures.

## Introduction

## Demo
* [API Document](https://study.nowstart.org)
* [h2](https://study.nowstart.org/h2-console)

<details>
<summary><h2>Dependencies</h2></summary>

* Java 17
* Spring-Boot 3.0.4
  * Spring-Security
  * JPA
  * Thymeleaf
  * Validation
  * Web
  * Openfeign
  * Devtool 
  * Jacoco
* Mapstruct 1.5.3
* SpringDoc 2.0.4
* Querydsl 5.0.0
* Jasypt 3.0.5
* DB
  * H2
  * MariaDB

</details>

## Getting Started
```shell
gradlew clean build -x test
```
* You must enter a jasypt password
```shell
java -Djasypt.encryptor.password=${PASSWORD} -Dspring.profiles.active=${PROFILE} -jar study-0.0.1-SNAPSHOT.jar
```
* If you don't know the jasypt password or want to use a different DB, you must create a application-${PROFILE}.yml file and enter a PROFILE environment variable
  * `url`: database url
  * `username`: database id
  * `password`: database password
```yaml
#### Spring
spring:
  #### Data Source
  datasource:
    url: 
    username: 
    password: 
```