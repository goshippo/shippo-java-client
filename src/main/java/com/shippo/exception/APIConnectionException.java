package com.shippo.exception;

public class APIConnectionException extends ShippoException {

    private static final long serialVersionUID = 1L;

    public APIConnectionException(String message) {
        super(message);
    }

    public APIConnectionException(String message, Throwable e) {
        super(message, e);
    }

}
