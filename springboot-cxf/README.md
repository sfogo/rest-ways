# Explore LOINC measurement and observation codes with SpringBoot + CXF + JSON / MOXY
## Prepare
* See [parent](https://github.com/sfogo/rest-ways) project for [LOINC](https://loinc.org) database setup.
* Export the following environment variables:
  * `DEMO_DB_URL`
  * `DEMO_DB_USERNAME`
  * `DEMO_DB_PASSWORD`
  * Optional `DEMO_DB_DRIVER_CLASSNAME` (defaults to MySQL with `com.mysql.jdbc.Driver`)

### Run with Spring Boot
* Default port is `8080`.
* If you installed the [parent](https://github.com/sfogo/rest-ways), directly run with `mvn spring-boot:run` (otherwise use `mvn compile spring-boot:run`).
* If you want ro run with port `7272`, you can do:  
`mvn clean spring-boot:run -Dserver.port=7272`
* Examples  
  * Get Code:  
`curl http://localhost:7272/loinc/codes/12345-5`  
  * Search Codes that contain `1234`  
`http://localhost:7272/loinc/codes?q=1234`

### CXF
Default CXF base path is `/services` but [application.yml](src/main/resources/application.yml) changes it to `/`.
