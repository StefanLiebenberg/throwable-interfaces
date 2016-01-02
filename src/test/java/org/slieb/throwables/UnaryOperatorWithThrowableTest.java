package org.slieb.throwables;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.UnaryOperatorWithThrowable.castUnaryOperatorWithThrowable;

public class UnaryOperatorWithThrowableTest implements FunctionInterfaceTestInterface {
    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castUnaryOperatorWithThrowable((a) -> {
            throw new Exception("");
        }).apply(null);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castUnaryOperatorWithThrowable((a) -> {
            throw new RuntimeException("");
        }).apply(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castUnaryOperatorWithThrowable((a) -> {
            throw new Error("");
        }).apply(null);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castUnaryOperatorWithThrowable((a) -> {
            throw new Throwable("");
        }).apply(null);
    }

    @Test
    public void testNormalOperation() {
        assertEquals(Integer.valueOf(2),
                castUnaryOperatorWithThrowable((Integer a) -> a + 1).apply(1));
    }

    @Override
    @Test
    public void testAnnotatedWithFunctionalInterface() {
        UnaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }
}