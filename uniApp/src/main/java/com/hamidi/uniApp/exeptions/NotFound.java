package com.hamidi.uniApp.exeptions;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
