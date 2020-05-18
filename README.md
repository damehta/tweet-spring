
# Problem Statement:
Create a Twitter like application for Internal Employees

### Features:
- Tweet (User can send tweet)
- User timeline 
    - User tweet (own tweets)
    - Home tweet (list of tweets by following users)
- Followers (List of followers)
- User profile (user ID, name, picture etc)

### Tech Stack:
- Java 1.8
- SpringBoot
- Mongo DB
- Docker 

### Setup
- Mongo DB 
    - Option 1: Download MongoDB and follow set-up instructions here: https://docs.mongodb.com/manual/installation/ 
    - Option 2: Run in docker container
        - docker pull mongo
        - Follow instructions here https://hub.docker.com/_/mongo
        - You can use src/test/resources/mongodb/* 
    - Create database and users as defined in application.properties file.

- Run Tweet springboot demo application
    - $ ./gradlew bootRun

- Containerize Tweet App
    - Create Docker image using /Dockerfile in this project
    - Refer this tutorial for more info: https://docs.docker.com/get-started/part2 

- Tweet GET and PUT APIs
    - Use Basic Auth
    - Curl command can be found in src/test/resources/PostmanCollection
