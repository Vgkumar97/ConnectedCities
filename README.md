## `ConnectedCities`

ConnectedCities is a Spring Boot application to check if two cities are connected. Two cities are considered connected if there’s a series of roads that can be traveled from one city
to another.

This application has implemented a Get rest API to return "Yes" if there is a path between to cities and "No" if there is no path between them.

#### `Technologies`

Spring Boot 2.0,<br>
Java 1.8 <br>
Maven <br>
Mockito for Unit testing. 
 
#### `Implementation Details`

Following are the brief description of source code and resources used in this maven project.

1. [Cities.txt](https://github.com/Vgkumar97/ConnectedCities/blob/master/src/main/resources/cities.txt) -
    Text file consists of pair of cities in each line with comma(,) separated.
2. [BuildConnectedCitiesGraphFromFile.java](https://github.com/Vgkumar97/ConnectedCities/blob/master/src/main/java/com/masterCard/connectedCities/config/BuildConnectedCitiesGraphFromFile.java) - 
    This class reads the textfile and places all the cities in Graph data structure.
3. [ConnectedCitiesGraph.java](https://github.com/Vgkumar97/ConnectedCities/blob/master/src/main/java/com/masterCard/connectedCities/service/ConnectedCitiesGraph.java) -
    This class is used to construct a graph for the given cities. 
4. [ConnectedCitiesController.java](https://github.com/Vgkumar97/ConnectedCities/blob/master/src/main/java/com/masterCard/connectedCities/controller/ConnectedCitiesController.java) -
    Rest controller to implement Get rest API- checkConnectedCity() which takes 2 inputs origin and destination as Query Parameters 
5. [ConnectedCitiesService.java](https://github.com/Vgkumar97/ConnectedCities/blob/master/src/main/java/com/masterCard/connectedCities/service/ConnectedCitiesService.java) -
    Service class to process the inputs and determine if two cities are connected.
6. Endpoints - test in postman.
   - http://localhost:8080/connected?origin=Boston&destination=Newark
   - http://localhost:8080/connected?origin=New York&destination=Philadelphia
   - http://localhost:8080/connected?origin=Philadelphia&destination=Trenton

#### `Unit Testing`

1. [ConnectedCitiesControllerTests.java](https://github.com/Vgkumar97/ConnectedCities/blob/master/src/test/java/com/masterCard/connectedCities/ConnectedCitiesControllerTests.java) -
    This class implements the unit test cases for Get rest API /connected.
2. [ConnectedCitiesServiceTests.java](https://github.com/Vgkumar97/ConnectedCities/blob/master/src/test/java/com/masterCard/connectedCities/ConnectedCitiesServiceTests.java) - 
    This class implements the unit test cases for service class methods.

#### `Build Status`

  - Success

#### `Cloning and Running`

  - This git repo can be cloned or download as a zip file from github.
  - Build the project from terminal 
        \ConnectedCities> mvn clean install  
    Shows BUILD SUCCESS on project compilation.
  - Run the below command from terminal to start the application.
        \ConnectedCities> java -jar target/ConnectedCities-0.0.1-SNAPSHOT.jar    
   Server will be started at the port 8080.
