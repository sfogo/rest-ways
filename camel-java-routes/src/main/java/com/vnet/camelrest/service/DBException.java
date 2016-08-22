package com.vnet.camelrest.service;

public class DBException extends ItemException {
    public DBException(String id, String message) {
        super(id, 400, 900, message);
    }
}
