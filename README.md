# Car Warehouse UI

This service developed using SparkJava web framework, Main purpose of this service is to enable great user interface for car warehouse users 

Below is url to access service

    http://localhost:4567/open/home
    

### Prerequisites to run service

Need below listed software/package installed on machine to run this service

    Java 8
    MongoDB

Create required user in mongoDB admin datastore, also created datastore with name HttpSessionDatastore

    use admin
    db.createUser(
    {
        user: "httpsessiondbuser",
        pwd: "password",
        roles: [ "readWrite", "dbAdmin" ]
    }
    )
    

### Interaction with other services

This service will interact with below services 

    car-warehouse-service

### Service configuration

Service configuration is located in below YAML file

    config/car-warehouse-ui.yml
    
### Build service

To build service, use below mentioned command

```
gradlew clean build
```

To skip the test while building, use below mentioned command

```
gradlew clean build -x test
```

### Run service

To run the service, use below mentioned command

```
gradlew clean build run
```
 
### Run unit test

To run unit test, use below mentioned command
```
gradlew test
```

### Run pitest

To run pitest, use below mentioned command (TBA)
```
gradlew pitest
```

### Run functional test

To run functional test, use below mentioned command (TBA)
```
gradlew functionalTest
```

### Test coverage

To run test coverage, run below command (TBA)

```
gradlew testCoverage
```

### Build vulnerability check

To make sure library we used in service not containing any vulnerability. Please run below task (TBA)

```
gradlew dependencyCheck
```


### Authors

* **Dipakkumar Bhoova** (https://github.com/bhuva3)
