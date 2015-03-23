package com.shippo.exception;

public abstract class ShippoException extends Exception {

    public ShippoException(String message) {
        super(message, null);
    }

    public ShippoException(String message, Throwable e) {
        super(message, e);
    }

    private static final long serialVersionUID = 1L;

}
