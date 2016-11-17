package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.UnaryOperatorWithThrowable.asUnaryOperatorWithThrowable;

public class UnaryOperatorWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asUnaryOperatorWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).apply(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asUnaryOperatorWithThrowable((v1) -> {
            throw new Error("expected error");
        }).apply(null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        UnaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asUnaryOperatorWithThrowable((v1) -> {
            return null;
        }).apply(null);
    }
}
