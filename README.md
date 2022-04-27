## Native Application with MongoDB

A Micronaut application that is built into a native application using GraalVM. The native application is then built 
into a Docker image. 

### Instructions
1. Create a volume to persist database
   ```
    docker volume create <volume-name>
   ```
2. Create a network
   ```
    docker network create <network-name>
    ```
3. Run a named mongoDB database container 
    ```
    docker run -d --name <db-container-name> --network <network-name> -v <volume-name>:/data/db -p 27017:27017 mongo
    ```
4. Build an image of the native application
    ```
   ./mvnw package -Dpackaging=docker-native -Pgraalvm   
    ```
5. Run this application as a named Docker container on the created network
    ```
    docker run -d --network <network-name> --name <app-container-name> -p 8080:8080 mongomicronaut:latest
    ```
______________________________________________________________________________________________________
[![Java CI with Maven](https://github.com/Vimbayinashe/mongo-micronaut-dessert-server/actions/workflows/maven.yml/badge.svg)](https://github.com/Vimbayinashe/mongo-micronaut-dessert-server/actions/workflows/maven.yml)
