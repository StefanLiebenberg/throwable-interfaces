package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntFunctionWithThrowable.asIntFunctionWithThrowable;

public class IntFunctionWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asIntFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).apply(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asIntFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).apply(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        IntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asIntFunctionWithThrowable((v1) -> {
            return null;
        }).apply(0);
    }
}
