package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongFunctionWithThrowable.castLongFunctionWithThrowable;

public class LongFunctionWithThrowableCastTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castLongFunctionWithThrowable((v1) -> {
            throw new Exception("expected error");
        }).apply(0);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castLongFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).apply(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castLongFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).apply(0);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castLongFunctionWithThrowable((v1) -> {
            throw new Throwable("expected throwable");
        }).apply(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        LongFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        castLongFunctionWithThrowable((v1) -> {
            return null;
        }).apply(0);
    }
}
