package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.PredicateWithThrowable.asPredicateWithThrowable;

public class PredicateWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asPredicateWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).test(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asPredicateWithThrowable((v1) -> {
            throw new Error("expected error");
        }).test(null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        PredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asPredicateWithThrowable((v1) -> {
            return false;
        }).test(null);
    }
}
