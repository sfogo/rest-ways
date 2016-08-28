#!/usr/bin/python3

import datetime
import sql_service as service
import loinc_service as loinc

# =====================
# MeasurementException
# =====================
class MeasurementException(Exception):
    def __init__(self,code,status,message):
        Exception.__init__(self,message)
        self.code=code
        self.status=status
    def __str__():
        return '{} {} {}'.format(code,status,message)

# =====================
# Get Measurement
# =====================
def getMeasurement(id):
    query = "select * from measurement where id = '{}'".format(id)
    item = service.selectOne(query)
    if (item==None):
        raise MeasurementException(101,404,'Cannot find measurement {}'.format(id))
    return item

# =====================
# Delete Measurement
# =====================
def deleteMeasurement(id):
    # Should not get beforehand
    getMeasurement(id)
    service.update("delete from measurement where id = '{}'".format(id))
    return 1

# =====================
# Get Measurements :
# Last 10 by default
# =====================
def getMeasurements(last=10):
    query = "select * from measurement order by captureTimestamp desc limit {}".format(last)
    return service.select(query)

# =====================
# Upload Measurement
# =====================
def upload(data):
    # Mandatory fields
    fieldNames = ['id','code','unit','value','deviceId','patientId']
    for i in range(0,len(fieldNames)):
        name = fieldNames[i]
        try: data[name]
        except Exception:
            raise MeasurementException(102,400,'Missing field {}'.format(name))
    
    # Check LOINC code and use long common name as
    # note if incoming data has none
    try:
        code = loinc.getCode(data['code'])
        try: data['note']
        except Exception: data['note'] = code['long_common_name']
    except loinc.LoincException as e:
        raise MeasurementException(e.code,e.status,e.args)

    # Default timestamps if not present
    fieldNames = ['capturetimestamp','creationtimestamp']
    for i in range(0,len(fieldNames)):
        name = fieldNames[i]
        try: value = data[name]
        except Exception:
            print('defaulting ' + name)
            data[name] = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

    # Default manual if not present
    try: value = data['manual']
    except Exception: data['manual'] = False

    # Create SQL statement
    sql = "insert into measurement (id,patientId,deviceId,code,unit,value,manual,captureTimestamp,creationTimestamp,note) values ('{}','{}','{}','{}','{}',{},{},'{}','{}','{}')".format(
        data['id'],
        data['patientId'],
        data['deviceId'],
        data['code'],
        data['unit'],
        data['value'],
        int(data['manual']),
        data['capturetimestamp'],
        data['creationtimestamp'],
        data['note'])

    count = service.update(sql)
    if (not(count>0)):
        raise MeasurementException(105,400,sql)

    return data
