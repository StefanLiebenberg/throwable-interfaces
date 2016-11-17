package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleToLongFunctionWithThrowable.castDoubleToLongFunctionWithThrowable;

public class DoubleToLongFunctionWithThrowableCastTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castDoubleToLongFunctionWithThrowable((v1) -> {
            throw new Exception("expected error");
        }).applyAsLong(0);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castDoubleToLongFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).applyAsLong(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castDoubleToLongFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).applyAsLong(0);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castDoubleToLongFunctionWithThrowable((v1) -> {
            throw new Throwable("expected throwable");
        }).applyAsLong(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        DoubleToLongFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        castDoubleToLongFunctionWithThrowable((v1) -> {
            return 0;
        }).applyAsLong(0);
    }
}
