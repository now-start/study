FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.security.password=${PASSWORD}", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
