#!/usr/bin/python3

import os

debug=True
dbConfig = {
  'type' : os.environ.get('DEMO_DB_TYPE'),
  'host' : os.environ.get('DEMO_DB_HOST'),
  'name' : os.environ.get('DEMO_DB_NAME'),
  'port' : os.environ.get('DEMO_DB_PORT'),
  'username' : os.environ.get('DEMO_DB_USERNAME'),
  'password' : os.environ.get('DEMO_DB_PASSWORD'),
}

if ('postgresql' == dbConfig['type']):
    import psycopg2 as connector
elif ('mysql' == dbConfig['type']):
    import mysql.connector as connector
else:
    raise Exception('Set DEMO_DB_TYPE envronment variable. Sypported values: postgresql | mysql');

# =====================
# Get Connection
# =====================
def getConnection():
    print('Connecting to %s at %s' % (dbConfig['name'],dbConfig['host']));
    return connector.connect(
        host=dbConfig['host'],
        port=dbConfig['port'],
        database=dbConfig['name'],
        user=dbConfig['username'],
        password=dbConfig['password'])

# ===================================================
# fix value : My and Postgre do not behave the same :
# My : null values retrieved as empty string
# Postgre : null values retrieved as None
# MySQL types :
# 246 : numeric value
# 253 : string
#   7 : timestamp
# PostgreSQL types
# 1700: numeric value
# 1043: string
#   25: text
# 1114: timestamp
# All this crazy testing to avoid invalid JSON null values
# TODO : need to find proper solution
# ===================================================
def fixValue(colType,value):
    if (colType==246 or colType==1700):
        return float(value)
    if (value is None and (colType==253 or colType==25 or colType==1043)):
        return ""
    return value

# =====================
# SQL Select
# =====================
def select(query):
    if (debug): print(query)
    connection = getConnection()
    cursor = connection.cursor()
    cursor.execute(query)
    metadata = cursor.description
    items = []
    for row in cursor:
        # Each row a catalog
        item = {}
        for col in range(0,len(metadata)):
            # colName is metadata[col][0]
            # type is metadata[col][1]
            colName=metadata[col][0]
            colType=metadata[col][1]
            item[colName] = fixValue(colType,row[col])
            # print("{}:{} = {}".format(colType, colName, item[colName]))
        items.append(item)

    # Closing
    cursor.close()
    connection.close()
    # Returned as list of catalogs
    return items

# =====================
# SQL Update
# =====================
def update(sql):
    if (debug): print(sql)
    connection = getConnection()
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    count = cursor.rowcount
    # Closing
    cursor.close()
    connection.close()
    # Return write count
    return count

# =====================
# SQL Select One
# =====================
def selectOne(query):
    items = select(query)
    if (len(items)>0):
        return items[0]
    else:
        return None
