package ru.kpfu.itis.exceptions;

public class NotValidCategoryException extends Exception {

    public NotValidCategoryException() {
        super();
    }

    public NotValidCategoryException(String message) {
        super(message);
    }

    public NotValidCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidCategoryException(Throwable cause) {
        super(cause);
    }
}
