package com.javarush.task.task26.task2613.exception;

import java.io.IOException;

public class InterruptOperationException extends Exception {
    public InterruptOperationException() {
    }

    public InterruptOperationException(String message) {
        super(message);
    }

    public InterruptOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterruptOperationException(Throwable cause) {
        super(cause);
    }

    public InterruptOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
