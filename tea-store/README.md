# Tea Store

## Intro
The store has member users and a set of different tea types that can be ordered by the users.

The application is using H2 database.

To start the application, you will need maven.
Please issue 'mvn spring-boot:run' command from the root directory of the project.
It will start the server on port 8085.
Once the server is up and running, you should be able to access the actuator health url: http://localhost:8085/store/actuator/health 
To run the tests, you could issue 'mvn clean test' command from the root directory of the project.