package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongToDoubleFunctionWithThrowable.castLongToDoubleFunctionWithThrowable;

public class LongToDoubleFunctionWithThrowableCastTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castLongToDoubleFunctionWithThrowable((v1) -> {
            throw new Exception("expected error");
        }).applyAsDouble(0);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castLongToDoubleFunctionWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).applyAsDouble(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castLongToDoubleFunctionWithThrowable((v1) -> {
            throw new Error("expected error");
        }).applyAsDouble(0);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castLongToDoubleFunctionWithThrowable((v1) -> {
            throw new Throwable("expected throwable");
        }).applyAsDouble(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        LongToDoubleFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        castLongToDoubleFunctionWithThrowable((v1) -> {
            return 0;
        }).applyAsDouble(0);
    }
}
