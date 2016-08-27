#!/usr/bin/python3

import os
import mysql.connector as connector

config = {
  'host' : os.environ.get('DEMO_DB_HOST'),
  'port' : os.environ.get('DEMO_DB_PORT'),
  'name' : os.environ.get('DEMO_DB_NAME'),
  'username' : os.environ.get('DEMO_DB_USERNAME'),
  'password' : os.environ.get('DEMO_DB_PASSWORD'),
}

connection=None
try:
    connection = connector.connect(
        host=config['host'],
        port=config['port'],
        database=config['name'],
        user=config['username'],
        password=config['password'])
except Exception as e:
    print(e.args)
finally:
    if (not(connection is None)): connection.close()
