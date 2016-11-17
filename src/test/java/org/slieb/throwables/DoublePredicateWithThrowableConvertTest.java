package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoublePredicateWithThrowable.asDoublePredicateWithThrowable;

public class DoublePredicateWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asDoublePredicateWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).test(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asDoublePredicateWithThrowable((v1) -> {
            throw new Error("expected error");
        }).test(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        DoublePredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asDoublePredicateWithThrowable((v1) -> {
            return false;
        }).test(0);
    }
}
