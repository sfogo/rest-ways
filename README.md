# Explore LOINC measurement and observation codes
## Overview
Different ways to explore [LOINC](https://loinc.org) codes with REST endpoints. This project does not provide LOINC data. It can be downloaded from [here](https://loinc.org/downloads) (_as well as database setup scripts_). Project is based on LOINC version **2.50**.

* [Camel CXF REST + XML](camel-cxf)
* [Camel Servlet REST + JSON](camel-java-routes)
* [Jersey + JSON](jersey) (Grizzly or [Webapp Runner](https://github.com/jsimone/webapp-runner) depending on POM profile)

## Install
* Once you have setup the database, you need to set the following environment variables:
  * `DEMO_DB_URL`
  * `DEMO_DB_USERNAME`
  * `DEMO_DB_PASSWORD`
  * Default driver class is `com.mysql.jdbc.Driver` but you can set a different one with environment variable `DEMO_DB_DRIVER_CLASSNAME`.
  * POM files depend on `mysql` / `mysql-connector-java` artifact but you should be able to change to any `JDBC` driver.
* `mvn install`  
This will do default packaging of all modules. Some modules have specific profiles (see within each).

## Run
* See specific instructions within each project.
