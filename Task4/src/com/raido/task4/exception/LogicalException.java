package com.raido.task4.exception;

public class LogicalException extends Exception {

    public LogicalException(String message) {
        super(message);
    }

    public LogicalException(Throwable cause) {
        super(cause);
    }

    public LogicalException(String message, Throwable cause) {
        super(message, cause);
    }

}
