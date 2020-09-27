# how to build and run locally
``./gradlew build && java -jar build/libs/man-backend-challenge-0.0.1-SNAPSHOT.jar``

# docker

## docker build
``docker build --build-arg JAR_FILE=build/libs/man-backend-challenge-0.0.1-SNAPSHOT.jar -t vw-ds/man-backend-challenge .`

## docker run
``docker run vw-ds/man-backend-challenge``