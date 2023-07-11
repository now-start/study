# study
![build](https://github.com/now-start/study/actions/workflows/build.yml/badge.svg)
![Coverage](https://github.com/now-start/study/blob/gh-pages/jacoco.svg)
![Branches](https://github.com/now-start/study/blob/gh-pages/branches.svg)

This project is designed to study Spring Boot 3.X, CI/CD, and various architectures.

## Demo
* [API Document](https://study.nowstart.org)
* [DataBase Console](https://study.nowstart.org/h2-console)

## Dependencies
<details>
<summary>Details</summary>

* Java 17
* Spring-Boot 3.0.4
* Mapstruct 1.5.3
* SpringDoc 2.0.4
* Querydsl 5.0.0
* Jasypt 3.0.5
* Jwt 0.9.1
* Jacoco plugin
* DB
  * H2
  * MariaDB

</details>

## Getting Started
```shell
gradlew clean build -x test
```
* You must enter a `${PASSWORD}`
```shell
java -Dspring.security.password=${PASSWORD} -Dspring.profiles.active=${PROFILE} -jar study-*.jar
```

* If you don't know the password or want to use a different DB, you must create a `application-${PROFILE}.yml` file and
  enter a `${PROFILE}` environment variable
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
