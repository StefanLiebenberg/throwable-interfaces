package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ConsumerWithThrowable.asConsumerWithThrowable;

public class ConsumerWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asConsumerWithThrowable((v1) -> {
            throw new RuntimeException("expected error");
        }).accept(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asConsumerWithThrowable((v1) -> {
            throw new Error("expected error");
        }).accept(null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        ConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asConsumerWithThrowable((v1) -> {
        }).accept(null);
    }
}
