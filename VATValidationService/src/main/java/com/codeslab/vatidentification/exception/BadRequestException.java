package com.codeslab.vatidentification.exception;

/**
 *
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(Throwable throwable) {
        super(throwable);
    }

    public BadRequestException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public BadRequestException(String string) {
        super(string);
    }

    public BadRequestException() {
        super();
    }
}
