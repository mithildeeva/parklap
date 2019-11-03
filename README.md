Build and Run the application

- In the current directory, run 
    
    `mvn clean install`
  
  This'll compile, run tests and build the jar under target/ as _parklap-1.0-SNAPSHOT.jar_

- To run end-to-end test for all existing commands run
    
    `mvn -Dtest=E2ETest test`

- To run the application, execute
    
    `java -jar target/parklap-1.0-SNAPSHOT.jar`
