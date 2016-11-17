package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.FunctionWithThrowable.castFunctionWithThrowable;

public class FunctionWithThrowableCastTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castFunctionWithThrowable((v1) -> {
            throw new Exception("expected error");
        }).apply(null);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).apply(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).apply(null);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castFunctionWithThrowable((v1) -> {
            throw new Throwable("expected throwable");
        }).apply(null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        FunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        castFunctionWithThrowable((v1) -> {
            return null;
        }).apply(null);
    }
}
