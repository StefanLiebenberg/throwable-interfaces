package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongBinaryOperatorWithThrowable.asLongBinaryOperatorWithThrowable;

public class LongBinaryOperatorWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asLongBinaryOperatorWithThrowable((v1, v2) -> {
            throw new RuntimeException("expected error");
        }).applyAsLong(0, 0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asLongBinaryOperatorWithThrowable((v1, v2) -> {
            throw new Error("expected error");
        }).applyAsLong(0, 0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        LongBinaryOperatorWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asLongBinaryOperatorWithThrowable((v1, v2) -> {
            return 0;
        }).applyAsLong(0, 0);
    }
}
