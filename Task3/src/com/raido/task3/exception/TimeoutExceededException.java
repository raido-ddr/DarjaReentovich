package com.raido.task3.exception;

/**
 * Created by Raido_DDR on 8/24/2014.
 */
public class TimeoutExceededException extends Exception {

    public TimeoutExceededException() {
        super();
    }

    public TimeoutExceededException(String message) {
        super(message);
    }

    public TimeoutExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeoutExceededException(Throwable cause) {
        super(cause);
    }

    public TimeoutExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
