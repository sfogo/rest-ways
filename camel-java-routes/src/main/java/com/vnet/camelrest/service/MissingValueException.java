package com.vnet.camelrest.service;

public class MissingValueException extends ItemException {
    public MissingValueException(String id) {
        super(id, 402, 103, "Missing Value");
    }
}
