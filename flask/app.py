#!/usr/bin/python3

import json
import datetime
import loinc_service as loinc
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
        js = json.dumps(item,default=dtEncoder)
        return Response(js, status=200, mimetype='application/json')
    except loinc.LoincException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        js = json.dumps(data)
        return Response(js, status=e.status, mimetype='application/json')
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        js = json.dumps(data)
        return Response(js, status=500, mimetype='application/json')

# =====================
# Get Codes
# =====================
@app.route("/loinc/codes", methods = ['GET'])
def getCodes():
    try:
        items = loinc.getCodes(request.args.get('q'),request.args.get('type'))
        js = json.dumps(items,default=dtEncoder)
        return Response(js, status=200, mimetype='application/json')
    except loinc.LoincException as e:
        data = {'error':{'code':e.code,'data':e.args}}
        js = json.dumps(data)
        return Response(js, status=e.status, mimetype='application/json')
    except Exception as e:
        data = {'error':{'code':999,'data':e.args}}
        js = json.dumps(data)
        return Response(js, status=500, mimetype='application/json')

# =====================
# Run
# =====================
if __name__ == "__main__":
    app.run()
