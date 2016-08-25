# Explore LOINC measurement and observation codes with Camel REST

## Foreword
Just a few Camel REST + JSON endpoints to explore [LOINC](https://loinc.org) codes. This project does not provide the data but it can be downloaded from [here](https://loinc.org/downloads) (_as well as database setup scripts_). Project is based upon [mysql](https://www.mysql.com) and LOINC version **2.50**.

## Run
* Export the following environment variables:
```
export DEMO_DB_URL=jdbc:mysql://<Host>/<DatabaseName>
export DEMO_DB_USERNAME=<username>
export DEMO_DB_PASSWORD=<password>
```
* If you installed the [parent](https://github.com/sfogo/rest-ways), directly run the java command below (otherwise do `mvn package` beforehand).  
`java -jar target/dependency/webapp-runner.jar --port 7171 target/camel-routes-0.1-SNAPSHOT`
* Go to home page `http://localhost:7171`.  It explains all endpoints.  
<img src="https://cloud.githubusercontent.com/assets/13286393/17844939/24d004f0-67f3-11e6-9a2a-19aeefaa3190.png"
     border="0" width="80%" />
