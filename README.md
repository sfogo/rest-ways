# Explore LOINC measurement and observation codes
## Overview
Different ways to explore [LOINC](https://loinc.org) codes with REST endpoints. This project does not provide LOINC data. It can be downloaded from [here](https://loinc.org/downloads) (_as well as database setup scripts_). Project is based on LOINC version **2.50**.

* [Camel CXF REST + XML](camel-cxf)
* [Camel Servlet REST + JSON](camel-java-routes)
* [Jersey + JSON](Jersey)

## Install
* Once you have setup the database, export the following environment variables:
```
export DEMO_DB_URL=jdbc:mysql://<Host>/<DatabaseName>
export DEMO_DB_USERNAME=<username>
export DEMO_DB_PASSWORD=<password>
```
* `mvn clean install`  
This will do default packing for all modules. Some modules have specific profiles (see within each).
