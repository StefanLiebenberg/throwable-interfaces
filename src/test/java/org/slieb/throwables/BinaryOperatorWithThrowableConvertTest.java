package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.BinaryOperatorWithThrowable.asBinaryOperatorWithThrowable;

public class BinaryOperatorWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asBinaryOperatorWithThrowable((v1, v2) -> {
            throw new RuntimeException("expected error");
        }).apply(null, null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asBinaryOperatorWithThrowable((v1, v2) -> {
            throw new Error("expected error");
        }).apply(null, null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        BinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asBinaryOperatorWithThrowable((v1, v2) -> {
            return null;
        }).apply(null, null);
    }
}
