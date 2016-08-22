package com.vnet.camelrest.service;

public class SearchItemException extends ItemException {
    public SearchItemException(String id, String message) {
        super(id, 400, 102, message);
    }
}
