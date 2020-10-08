# TODO: use var with jar destination file to reuse among COPY and ENTRYPOINT commands
FROM openjdk:11-jre
ARG JAR_FILE=build/libs/man-backend-challenge-0.0.1.jar
ARG JAR_DESTINATION_FILE=/fota-vehicles-compatibility/app.jar
COPY ${JAR_FILE} ${JAR_DESTINATION_FILE}
RUN mkdir -p /fota-vehicles-compatibility/code-files-to-sweep-dir
ENTRYPOINT ["java","-jar","/fota-vehicles-compatibility/app.jar"]