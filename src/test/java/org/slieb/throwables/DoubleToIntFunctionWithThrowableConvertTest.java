package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleToIntFunctionWithThrowable.asDoubleToIntFunctionWithThrowable;

public class DoubleToIntFunctionWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asDoubleToIntFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).applyAsInt(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asDoubleToIntFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).applyAsInt(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        DoubleToIntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asDoubleToIntFunctionWithThrowable((v1) -> {
            return 0;
        }).applyAsInt(0);
    }
}
