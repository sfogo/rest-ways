# Explore LOINC measurement and observation codes with Camel REST

## Foreword
Just a few Camel REST + JSON endpoints to explore [LOINC](https://loinc.org) codes. This project does not provide the data but it can be downloaded from [here](https://loinc.org/downloads) (_as well as database setup scripts_). Project is based on LOINC version **2.50**.

## Run
* Export the following environment variables:
```
export DEMO_DB_URL=jdbc:mysql://<Host>/<DatabaseName>
export DEMO_DB_USERNAME=<username>
export DEMO_DB_PASSWORD=<password>
```

### Grizzly
* Default port is `8080` but you can also set a different value with  
`export DEMO_REST_PORT=7272`
* If you installed the [parent](https://github.com/sfogo/rest-ways), directly run with `mvn exec:java` (otherwise use `mvn compile exec:java`).
* Examples  
Get Code:  
`curl http://localhost:7272/app/loinc/codes/12345-5`  
Search Codes that contain `1234`  
`http://localhost:7272/app/loinc/codes?q=1234`

### Webapp Runner
* You need to package it with `webapp-runner` profile:  
`mvn -Pwebapp-runner package`
* Run  
`java -jar target/dependency/webapp-runner.jar --port 7272 target/jersey-0.1-SNAPSHOT`
* Same URLs as with Grizzly
