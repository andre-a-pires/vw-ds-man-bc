FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
FROM library/postgres
ENV POSTGRES_USER admin
ENV POSTGRES_PASSWORD 8QYRjyZGxM!UyT^3KhGX
ENV POSTGRES_DB fota