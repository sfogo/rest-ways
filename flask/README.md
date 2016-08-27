
# Explore LOINC measurement and observation codes with Python3 + [Flask](http://flask.pocoo.org)
## Before you run

## Run
* See [parent](https://github.com/sfogo/rest-ways) project for [LOINC](https://loinc.org) database setup.
* Export the following environment variables:
  * `DEMO_DB_TYPE` Supported values are `mysql` and `postgresql`. It tells SQL Service which SQL connector module to import (`mysql.conector` or `psycopg2`)
  * `DEMO_DB_HOST`
  * `DEMO_DB_PORT` Usually `3306` for `MySql` and `5432` for `PostgreSQL`
  * `DEMO_DB_NAME`
  * `DEMO_DB_USERNAME`
  * `DEMO_DB_PASSWORD`
