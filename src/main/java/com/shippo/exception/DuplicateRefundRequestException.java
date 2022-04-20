package com.shippo.exception;

public class DuplicateRefundRequestException extends InvalidRequestException {

    private static final long serialVersionUID = 1L;
    public static final String refundMessagePattern = "Refund with this Transaction already exists.";

    public DuplicateRefundRequestException(String message, String param, Throwable e) {
        super(message, param, e);
    }

}
