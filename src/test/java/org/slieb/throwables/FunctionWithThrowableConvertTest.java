package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.FunctionWithThrowable.asFunctionWithThrowable;

public class FunctionWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).apply(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).apply(null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        FunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asFunctionWithThrowable((v1) -> {
            return null;
        }).apply(null);
    }
}
