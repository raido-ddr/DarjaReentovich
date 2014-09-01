package com.raido.task3.exception;

/**
 * Created by Raido_DDR on 8/24/2014.
 */
public class ResourceException extends  Exception {

    public ResourceException() {
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
