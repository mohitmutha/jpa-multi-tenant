## Overview ##
This is a sample implementation of multi-tenancy implementation using Spring-JPA and Hibernate. The below patterns are implemented
1. Separate databases for each tenant
2. Same database and separate schemas for each tenant
3. Same database same schema and separation of tenants data using discriminator. This is implemented in the `tenant-discriminator` branch

The code is mostly inspired from this [article](https://medium.com/swlh/multi-tenancy-implementation-using-spring-boot-hibernate-6a8e3ecb251a) 

## Pre-Requisites ##
1. Docker is installed 
2. Ports 9090 and 8080 are free. Adminer runs on port 9090. Change the `docker-compose.yaml` in case this is not required. 

## Compiling the code and creating the image ##
1. Run the below command to compile the code and create a docker container
```
    mvn clean jib:dockerBuild
```
A docker image `mohitmutha/jpamultitenant` will be created

## Running the code ##
1. Locate the `docker-compose.yaml` located in the project root directory
2. Change the line below to select a one of the above patterns
``` $yaml
   ....
    app:
        ...
        environment:
           MULTITENTANT_STRATEGY: DATABASE # SCHEMA / DISCRIMINATOR
```
3. Run the services defined in the docker compose definition
```$xslt
docker-compose up -d 
```

This will create the below services
- Postgres Database  
- App service (Port: 8080)
- Adminer (Port 9090) (Optional)

##### DBScripts #####
The script under `dbscripts/init-database.sh` creates the DB and sets up the databases, tables and schemas required. Currently all the schemas and databases required by all three patterns are created in the same script. So there is some redundancy.  