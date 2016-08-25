package com.vnet.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlRootElement
public class ErrorDetails extends HashMap<String,Object> {

    final private HashMap<String,Object> error = new HashMap<>();
    public ErrorDetails() {
        put("error", error);
    }

    public void setCode(int code) {error.put("code", code);}
    public int getCode() {return Integer.valueOf(error.get("code").toString());}

    public String getValue() {return error.get("value").toString();}
    public void setValue(String value) {error.put("value",value);}

    public void setAddlData(Object data) {error.put("data", data);}
    public Object getAddlData() {return error.get("data");}

    public String getMessage() {return error.get("message").toString();}
    public void setMessage(String message) {error.put("message", message);}
}
