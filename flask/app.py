#!/usr/bin/python3

import os
import json
import datetime
import loinc_service as loinc
import measurement_service as measurements
from flask import Flask
from flask import Response
from flask import request
app = Flask(__name__)

# =====================
# Format timestamps
# =====================
dtEncoder = lambda o: (
     o.isoformat() if isinstance(o, datetime.datetime) or isinstance(o, datetime.date)
     else None)

# =====================
# Make Response
# =====================
def makeResponse(data,status,defEncoder=None):
    js = json.dumps(data,default=defEncoder)
    return Response(js, status=status, mimetype='application/json')

# =====================
# Routes
# =====================
@app.route("/")
def hello():
    return app.send_static_file('home.html')

# =====================
# Get Code
# =====================
@app.route("/loinc/codes/<id>", methods = ['GET'])
def getCode(id):
    try:
        item = loinc.getCode(id)
        return makeResponse(item,200,dtEncoder)
    except loinc.LoincException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        return makeResponse(data,e.status)
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        return makeResponse(data,500)

# =====================
# Get Codes
# =====================
@app.route("/loinc/codes", methods = ['GET'])
def getCodes():
    try:
        items = loinc.getCodes(request.args.get('q'),request.args.get('type'))
        return makeResponse(items,200,dtEncoder)
    except loinc.LoincException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        return makeResponse(data,e.status)
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        return makeResponse(data,500)

# =====================
# Get Measurement
# =====================
@app.route("/loinc/measurements/<id>", methods = ['GET'])
def getMeasurement(id):
    try:
        item = measurements.getMeasurement(id)
        return makeResponse(item,200,dtEncoder)
    except measurements.MeasurementException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        return makeResponse(data,e.status)
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        return makeResponse(data,500)

# =====================
# Delete Measurement
# =====================
@app.route("/loinc/measurements/<id>", methods = ['DELETE'])
def deleteMeasurement(id):
    try:
        count = measurements.deleteMeasurement(id)
        return makeResponse({'deleted':count},200)
    except measurements.MeasurementException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        return makeResponse(data,e.status)
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        return makeResponse(data,500)

# =====================
# Get Measurements
# =====================
@app.route("/loinc/measurements", methods = ['GET'])
def getMeasurements():
    try:
        last = request.args.get('last')
        if (last is None):
            limit=10
        else:
            limit=int(last)
        items = measurements.getMeasurements(limit)
        return makeResponse(items,200,dtEncoder)
    except measurements.MeasurementException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        return makeResponse(data,e.status)
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        return makeResponse(data,500)

# =====================
# POST Measurement
# =====================
@app.route("/loinc/measurements", methods = ['POST'])
def upload():
    try:
        item = measurements.upload(request.get_json())
        return makeResponse(item,200,dtEncoder)
    except measurements.MeasurementException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        return makeResponse(data,e.status)
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        return makeResponse(data,500)

# =====================
# Run
# =====================
if __name__ == "__main__":
    app.run(port=int(os.environ.get('DEMO_REST_PORT', 5000)),threaded=True)
