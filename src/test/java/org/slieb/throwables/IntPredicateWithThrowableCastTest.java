package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntPredicateWithThrowable.castIntPredicateWithThrowable;

public class IntPredicateWithThrowableCastTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castIntPredicateWithThrowable((v1) -> {
            throw new Exception("expected error");
        }).test(0);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castIntPredicateWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).test(0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castIntPredicateWithThrowable((v1) -> {
            throw new Error("expected error");
        }).test(0);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castIntPredicateWithThrowable((v1) -> {
            throw new Throwable("expected throwable");
        }).test(0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        IntPredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        castIntPredicateWithThrowable((v1) -> {
            return false;
        }).test(0);
    }
}
