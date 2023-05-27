FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV PASSWORD ""
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-Dspring.profiles.active=$PASSWORD", "-jar", "app.jar"]
