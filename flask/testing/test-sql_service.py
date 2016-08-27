#!/usr/bin/python3

import sys
sys.path.insert(0,'..')

import json
import datetime
import sql_service as service

# =====================
# Format timestamps
# =====================
dtEncoder = lambda o: (
     o.isoformat() if isinstance(o, datetime.datetime) or isinstance(o, datetime.date)
     else None)

query = "select * from loinc where loinc_num = '12345-5'"
print(query)
items = service.sqlSelect(query)
print(len(items))
print(json.dumps(items,default=dtEncoder))

query = "select loinc_num, long_common_name, class from loinc where loinc_num like '%1234%'"
print(query)
items = service.sqlSelect(query)
print(len(items))
print(json.dumps(items))
