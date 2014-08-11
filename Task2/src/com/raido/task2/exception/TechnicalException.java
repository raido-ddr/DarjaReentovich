package com.raido.task2.exception;

/**
 * Created by Raido_DDR on 8/5/2014.
 */
public class TechnicalException extends Exception {

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
