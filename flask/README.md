
# Explore LOINC measurement and observation codes with Python3 + [Flask](http://flask.pocoo.org)
## Before you run
### Environment
* Python3 Environment  
```
$ uname -a 
Linux dm4 4.4.0-34-generic #53~14.04.1-Ubuntu SMP Wed Jul 27 16:56:40 UTC 2016 x86_64 x86_64 x86_64 GNU/Linux
$ lsb_release -a
No LSB modules are available.
Distributor ID:	Ubuntu
Description:	Ubuntu 14.04.5 LTS
Release:	14.04
Codename:	trusty
$ which python3
/usr/bin/python3
```

### Install Flask
* General install instructions are [here](http://flask.pocoo.org/docs/0.11/installation)
* You can do a system wide install and not bother about [virtualenv](http://docs.python-guide.org/en/latest/dev/virtualenvs) at all. Here is what I did for virtual env:
  * `sudo apt-get install python3-pip`
  * `sudo pip3 install virtualenv`
  * `cd flask` (directory that contains [app.py](app.py) file)
  * `sudo virtualenv venv` : shell prompt gets prefixed with `(venv)`
  * `sudo pip3 install Flask`

### Database Client
  * Application also needs either `MySQL` or `PostgreSQL` Python client. Like Flax, you can choose to install system wide or within `venv`. You can check whether you already them with [this](testing/test-mysql.sql) for MySQL or [this](testing/test-pg.py) for PostgresSQL.
  * PostgreSQL python client can be installed with  
`sudo pip3 install psycopg2`  
First time it complained about not having `pg_config`, which I got with `sudo apt-get install libpq-dev`, then re-attempt `psycopg2` install.
  * MySQL python client can be installed with  
`sudo pip3 install mysql-connector`

## Run
* See [parent](https://github.com/sfogo/rest-ways) project for [LOINC](https://loinc.org) database setup.
* Once you have Flaks and database client, export the following environment variables:
  * `DEMO_DB_TYPE` Supported values are `mysql` and `postgresql`. It tells SQL Service which SQL connector module to import (`mysql.conector` or `psycopg2`)
  * `DEMO_DB_HOST`
  * `DEMO_DB_PORT` Usually `3306` for `MySql` and `5432` for `PostgreSQL`
  * `DEMO_DB_NAME`
  * `DEMO_DB_USERNAME`
  * `DEMO_DB_PASSWORD`
* Run with  
`./app.py`   
` * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)`
* CTRL+C to shutdown Flask. You can also exit the virtual environment with `deactivate` (shell prompt goes back to normal).
* Default port is`5000` but you can set a different value with environment variable `DEMO_REST_PORT`.

