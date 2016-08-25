package com.vnet.service;

import com.vnet.model.ErrorDetails;
import com.vnet.model.SimpleError;

public class ServiceException extends Exception {
    final private Code code;
    final private String[] data;

    public enum Code {
        SQL(199, "SQL Error", 500),
        MISSING(100, "Missing value", 400),
        NOT_FOUND(101, "Item not found", 404),
        MQP(102, "Missing query parameter", 400),
        ILV(103, "Invalid limit value", 400),
        IST(104, "Invalid search type", 400);
        private int value;
        private String message;
        private int status;
        Code(int value, String message, int status) {
            this.value = value;
            this.message = message;
            this.status = status;
        }
        public int getValue() {return this.value;}
        public String getMessage() {return this.message;}
        public int getStatus() {return status;}
    }

    public Code getCode() {return code;}
    public String[] getData() {return this.data;}

    public ServiceException(Code code, String... data) {
        super(code.getMessage());
        this.code = code;
        this.data = data;
    }

    public ServiceException(Code code) {
        super(code.getMessage());
        this.code = code;
        this.data = null;
    }

    public ErrorDetails asErrorDetails() {
        final ErrorDetails details = new ErrorDetails();
        details.setCode(code.value);
        details.setMessage(code.message);
        if (data != null) details.setAddlData(data);
        return details;
    }

    public SimpleError asError() {
        final SimpleError error =new SimpleError();
        error.setCode(code.value);
        String message = code.message;
        if (data != null) {for (String s : data) message += " [" + s + "]";}
        error.setMessage(message);
        return error;
    }
}