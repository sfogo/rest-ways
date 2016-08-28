#!/usr/bin/python3

import sql_service as service

# =====================
# LoincException
# =====================
class LoincException(Exception):
    def __init__(self,code,status,message):
        Exception.__init__(self,message)
        self.code=code
        self.status=status
    def __str__():
        return '{} {} {}'.format(code,status,message)

# =====================
# Get Code
# =====================
def getCode(code):
    query = "select * from loinc where loinc_num = '{}'".format(code)
    item = service.selectOne(query)
    if (item==None):
        raise LoincException(101,404,'Cannot find LOINC code {}'.format(code))
    return item

# =====================
# Get Codes
# =====================
def getCodes(q,searchType=None):
    if (q==None):
        raise LoincException(102,400,'Missing query parameter q')
    elif (searchType is None or searchType=='code'):
        query = "select * from loinc where loinc_num like '%{}%'".format(q)
    elif (searchType=='name'):
        query = "select * from loinc where lower(long_common_name) like '%{}%'".format(q)
    else:
        raise LoincException(103,400,'Invalid search type {}'.format(searchType))
    # Return items
    return service.select(query)
