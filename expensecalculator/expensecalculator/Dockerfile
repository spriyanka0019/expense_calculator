# FROM eclipse-temurin
FROM eclipse-temurin:17-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
ADD target/spring-boot-docker.jar spring-boot-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]


# FROM eclipse-temurin:17-jdk-alpine
# VOLUME /tmp
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]