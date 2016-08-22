# Explore LOINC measurement and observation codes with Camel REST
## Foreword
Just a few Camel REST endpoints to explore [LOINC](https://loinc.org) codes. This project does not provide the data it can be downloaded from [here](https://loinc.org/downloads) (_as well as database setup scripts_). Project is based upon [mysql](https://www.mysql.com) and LOINC version **2.50**.
## Deploy
* Export the following environment variables:
```
export DEMO_DB_URL=jdbc:mysql://<Host>/<DatabaseName>
export DEMO_DB_USERNAME=<username>
export DEMO_DB_PASSWORD=<password>
```
* `mvn package`  
`java -jar target/dependency/webapp-runner.jar --port 7171 target/loinc-0.1.0`
* Go to home page `http://localhost:7171`.  It explains all endpoints.  
<img src="https://cloud.githubusercontent.com/assets/13286393/17844831/502115f0-67f2-11e6-95ec-ef5333b28471.png"
     border="0" width="80%" />
