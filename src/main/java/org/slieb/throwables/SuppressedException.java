package org.slieb.throwables;


public class SuppressedException extends RuntimeException {
    public SuppressedException(Throwable cause) {
        super(cause);
    }

}
