package com.shippo.exception;

public class RequestTimeoutException extends ShippoException {

    private static final long serialVersionUID = 1L;

    private String param;

    public RequestTimeoutException(String message, String param, Throwable e) {
        super(message, e);
        this.param = param;
        
    }

    public RequestTimeoutException(String message) {
        super(message);
    }

    public String getParam() {
        return param;
    }

}
