# BOOK BUS BACKEND
### 1. Technologies
* Java 1.8
* Maven 4.0
* Spring BOOT 2.0
* Srping Cloud // FEATURE
* Srping Integration Kafka
* Spring Data JPA
* PostgreSql
* Undertow web server
* Redis // FEATURE
* Lombok
* Mapstruct

### 2. Install
 - zookeeper - tested on version 3.4.11
 - kafka - tested on version 2.12-1.0.0
 - postgresql - tested on version 9.6
 - create db 'adidas'

### 3. Run app 
 - Run zookeeper. From the zookeeper bin folder:<br>
```shell
zkserver
``` 
 - Run kafka broker. From the kafka folder run the command (for windows):<br>
(notes: maybe you need to clean folder "kafka-logs" and "zookeeper-*.data" if broker doesn't start)<br>
```shell
.\bin\windows\kafka-server-start.bat .\config\server.properties
``` 
 - Start modules:
 ```shell
 BackOfficeApplication
 MidofficeApplication
 ``` 
- In your browser run
 ```shell
http://localhost:8082/route/MAD/LON
 ``` 
It will return shortest route and the one with the less stops
 ```shell
{
    "shortestRoute": [
        "MAD",
        "BCN",
        "PAR",
        "LON"
    ],
    "lessStopsRoute": [
        "MAD",
        "VLC",
        "LON"
    ]
}
 ``` 
###4. Get API Documentation (Swagger)
Notes: see documentation http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 - Run target web module
 - Open documentation of the module in your browser <br>
Pattern: http://<host>:<port>/<app-root>/swagger-ui.html<br>
e.g.:
```shell
http://localhost:8082/swagger-ui.html
``` 
 - Get documentation as JSON<br>
Pattern: http://<host>:<port>/<app-root>/v2/api-docs<br>
e.g.:
```shell
http://localhost:8082/v2/api-docs
``` 
