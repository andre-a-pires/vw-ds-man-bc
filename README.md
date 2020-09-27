# how to build and run locally
``./gradlew clean build && java -jar build/libs/*.jar``

# docker

## docker build
``docker build --build-arg JAR_FILE=build/libs/vw-ds-man-bc-0.0.1.jar -t vw-ds/man-backend-challenge .``

## docker run
``docker run -p 8080:8080 vw-ds/man-backend-challenge``
