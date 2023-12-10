package com.kkk.fleetmanagement.v1.common.exception;

public class ShipmentException extends Exception {
    public ShipmentException(String message) {
        super(message);
    }

    public ShipmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
