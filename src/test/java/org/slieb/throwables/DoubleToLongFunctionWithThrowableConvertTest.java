package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleToLongFunctionWithThrowable.asDoubleToLongFunctionWithThrowable;

public class DoubleToLongFunctionWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asDoubleToLongFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).applyAsLong(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asDoubleToLongFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).applyAsLong(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        DoubleToLongFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asDoubleToLongFunctionWithThrowable((v1) -> {
            return 0;
        }).applyAsLong(0);
    }
}
