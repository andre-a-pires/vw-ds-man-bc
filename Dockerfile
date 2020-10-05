FROM openjdk:11-jre
ARG JAR_FILE=build/libs/man-backend-challenge-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]