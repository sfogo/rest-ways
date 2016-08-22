package com.vnet.camelrest.service;

public class ItemNotFoundException extends ItemException {
    public ItemNotFoundException(String id) {
        this(id, "Item Not Found");
    }

    public ItemNotFoundException(String id, String message) {
        super(id, 400, 101, message);
    }
}
