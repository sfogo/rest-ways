package com.vnet.camelrest.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public abstract class ItemException extends Exception {
    private final String id;
    private int httpResponseCode;
    private final int code;

    public String getId() {
        return id;
    }

    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    public int getCode() {
        return code;
    }

    protected ItemException(String id, int httpResponseCode, int code, String message) {
        super(message);
        this.id = id;
        this.httpResponseCode = httpResponseCode;
        this.code = code;
    }

    public String toString() {
        return "ERR-" + code + ":" + (id==null ? getMessage() : getMessage() + " : " + id);
    }

    public String getJsonError() {
        final Map<String,String> error = new HashMap<>();
        error.put("code",String.valueOf(code));
        error.put("object.id",id);
        error.put("message",getMessage());
        final Map<String,Object> map = new HashMap<>();
        map.put("error",error);
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return toString();
        }
    }
}
