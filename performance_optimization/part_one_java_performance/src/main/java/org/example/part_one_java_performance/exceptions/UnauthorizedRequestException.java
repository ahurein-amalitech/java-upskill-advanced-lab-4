package org.example.part_one_java_performance.exceptions;

public class UnauthorizedRequestException extends  RuntimeException{
    public UnauthorizedRequestException() {
        super("Unauthorized request");
    }

    public UnauthorizedRequestException(String message) {
        super(message);
    }

    public UnauthorizedRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedRequestException(Throwable cause) {
        super(cause);
    }
}
