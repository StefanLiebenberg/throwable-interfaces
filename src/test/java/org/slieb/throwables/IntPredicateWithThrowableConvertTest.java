package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntPredicateWithThrowable.asIntPredicateWithThrowable;

public class IntPredicateWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asIntPredicateWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).test(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asIntPredicateWithThrowable((v1) -> {
            throw new Error("expected error");
        }).test(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        IntPredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asIntPredicateWithThrowable((v1) -> {
            return false;
        }).test(0);
    }
}
