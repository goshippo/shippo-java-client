package com.shippo.exception;

public class APIException extends ShippoException {

    private static final long serialVersionUID = 1L;

    public APIException(String message, Throwable e) {
        super(message, e);
    }

}
