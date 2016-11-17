package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToIntFunctionWithThrowable.asToIntFunctionWithThrowable;

public class ToIntFunctionWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asToIntFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).applyAsInt(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asToIntFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).applyAsInt(null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        ToIntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asToIntFunctionWithThrowable((v1) -> {
            return 0;
        }).applyAsInt(null);
    }
}
