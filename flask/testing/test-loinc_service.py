#!/usr/bin/python3

import sys
sys.path.insert(0,'..')

import json
import loinc_service as service

item = service.getCode('12345-5')
print(item)

try:
    item = service.getCode('12345-56')
    print(item)
except service.LoincException as e:
    print(e.args)

items = service.getCodes('cannab','name');
print(len(items))

try:
    items = service.getCodes('cannab','qname');
    print(len(items))
except service.LoincException as e:
    print(e.args)
