package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToLongFunctionWithThrowable.asToLongFunctionWithThrowable;

public class ToLongFunctionWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asToLongFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).applyAsLong(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asToLongFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).applyAsLong(null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        ToLongFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asToLongFunctionWithThrowable((v1) -> {
            return 0;
        }).applyAsLong(null);
    }
}
