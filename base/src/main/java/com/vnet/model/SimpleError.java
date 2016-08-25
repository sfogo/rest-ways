package com.vnet.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Error")
public class SimpleError {
    public SimpleError() {}
    private int code;
    private String message;
    public int getCode() {return code;}
    public void setCode(int code) {this.code = code;}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
}
