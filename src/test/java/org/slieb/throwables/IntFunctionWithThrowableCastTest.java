package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntFunctionWithThrowable.castIntFunctionWithThrowable;

public class IntFunctionWithThrowableCastTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castIntFunctionWithThrowable((v1) -> {
            throw new Exception("expected error");
        }).apply(0);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castIntFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).apply(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castIntFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).apply(0);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castIntFunctionWithThrowable((v1) -> {
            throw new Throwable("expected throwable");
        }).apply(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        IntFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        castIntFunctionWithThrowable((v1) -> {
            return null;
        }).apply(0);
    }
}
