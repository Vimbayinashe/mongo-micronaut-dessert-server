## Micronaut Native Application

A Micronaut native application with a MongoDB Replica Set that is packaged into a Docker image. 

### Download an image of the application
   ```
    docker pull ghcr.io/vimbayinashe/mongo-micronaut-dessert-server:latest
   ```

### Step-by-step Instructions
1. Create a network
    ```
    docker network create <network-name>
    ``` 
2. Run **n** times to create **n** volumes to persist databases
   ```
   docker volume create <volume-name>
   ```
3. Run **n** times to create a Mongo Replica Set with **n** database containers
    ```
    docker run -d --name <db-container-name> --network <network-name> -v <volume-name>:/data/db
    mongo mongod --replSet <replica-set-name>
    ```
4. Download and run this application as a named Docker container on the created network
    ```
    docker run -d --network <network-name> --name <app-container-name> -p 8080:8080
    ghcr.io/vimbayinashe/mongomicronaut:latest
    ```
### Endpoints

| HTTP | Path      | Information      | Status Code |
|------|-----------|------------------|-------------|
| GET  | /desserts | Get all desserts | 200         |
| POST | /desserts | Save a dessert   | 201         |

#### Example of GET Response Body:
```
[
  {
    "name": "chocolate brownie"
  },
  {
    "name": "Carrot cake",
    "description": "Spicy carrot cake and cream cheese frosting"
  }
]
```

#### Example of POST Request Body:     
**name** - required      
**description** - optional
```
  {
    "name": "Carrot cake",
    "description": "Spicy carrot cake and cream cheese frosting"
  }
```
