.DEFAULT_GOAL := help

help: ## this help
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

build: ## builds java app with gradle
	./gradlew build

run: ## runs jar
	java -jar build/libs/*.jar

clean: ## cleans jar with gradle
	./gradlew clean

docker-build: ## builds docker image with name vw-ds/man-backend-challenge
	docker build --build-arg JAR_FILE=build/libs/vw-ds-man-bc-0.0.1.jar -t vw-ds/man-backend-challenge .

docker-run: ## runs docker image vw-ds/man-backend-challenge
	docker run -p 8080:8080 vw-ds/man-backend-challenge