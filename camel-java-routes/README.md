# Explore LOINC measurement and observation codes with Camel REST

## Foreword
* See [parent](https://github.com/sfogo/rest-ways) project for [LOINC](https://loinc.org) database setup.
* Just a few Camel REST + JSON endpoints to explore [LOINC](https://loinc.org) codes.

## Run
* Set the following environment variables:
  * `DEMO_DB_URL`
  * `DEMO_DB_USERNAME`
  * `DEMO_DB_PASSWORD`
  * Optional `DEMO_DB_DRIVER_CLASSNAME` (defaults to MySQL with `com.mysql.jdbc.Driver`)

* If you installed the [parent](https://github.com/sfogo/rest-ways), directly run the java command below (otherwise do `mvn package` beforehand).  
`java -jar target/dependency/webapp-runner.jar --port 7171 target/camel-routes-0.1-SNAPSHOT`
* Go to home page `http://localhost:7171`.  It explains all endpoints.  
<img src="https://cloud.githubusercontent.com/assets/13286393/17844939/24d004f0-67f3-11e6-9a2a-19aeefaa3190.png"
     border="0" width="80%" />
