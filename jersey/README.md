# Explore LOINC measurement and observation codes with Jersey + JSON
## Run
* See [parent](https://github.com/sfogo/rest-ways) project for [LOINC](https://loinc.org) database setup.
* Export the following environment variables:
  * `DEMO_DB_URL`
  * `DEMO_DB_USERNAME`
  * `DEMO_DB_PASSWORD`
  * Optional `DEMO_DB_DRIVER_CLASSNAME` (defaults to MySQL with `com.mysql.jdbc.Driver`)

### Grizzly
* Default port is `8080` but you can also set a different value with  
`export DEMO_REST_PORT=7272`
* If you installed the [parent](https://github.com/sfogo/rest-ways), directly run with `mvn exec:java` (otherwise use `mvn compile exec:java`).
* Examples  
  * Get Code:  
`curl http://localhost:7272/app/loinc/codes/12345-5`  
  * Search Codes that contain `1234`  
`http://localhost:7272/app/loinc/codes?q=1234`

### Webapp Runner
* [web.xml](https://github.com/sfogo/rest-ways/blob/master/jersey/src/main/webapp/WEB-INF/web.xml) file is used to enable [resource](https://github.com/sfogo/rest-ways/blob/master/jersey/src/main/java/com/vnet/jersey/LoincResource.java) endpoints. See [Jersey Guide](https://jersey.java.net/documentation/latest/user-guide.html#deployment.servlet.3) for Deployment using web.xml descriptor.
* You need to package it with `webapp-runner` profile:  
`mvn -Pwebapp-runner package`
* Run  
`java -jar target/dependency/webapp-runner.jar --port 7272 target/jersey-0.1-SNAPSHOT`
* Same URLs as with Grizzly
