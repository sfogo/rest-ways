# Explore LOINC measurement and observation codes with Camel REST
## Foreword
Just a few Camel REST endpoints to explore [LOINC](https://loinc.org) codes. This project does not provide the data. Data and database setup can be downloaded from [here](https://loinc.org/downloads). Project is based upon [mysql](https://www.mysql.com) and LOINC version **2.50**.
## Deploy
* Export the following environment variables:
```
export DEMO_DB_URL=jdbc:mysql://<Host>/<DatabaseName>
export DEMO_DB_USERNAME=<username>
export DEMO_DB_PASSWORD=<password>
```
* `mvn package`  
`java -jar target/dependency/webapp-runner.jar --port 7171 target/loinc-0.1.0`
* Go to `http://localhost:7171`  
Home page explains all endpoints.

