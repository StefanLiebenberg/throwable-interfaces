package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongSupplierWithThrowable.asLongSupplierWithThrowable;

public class LongSupplierWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asLongSupplierWithThrowable(() -> {
            throw new RuntimeException("expected error");
        }).getAsLong();
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asLongSupplierWithThrowable(() -> {
            throw new Error("expected error");
        }).getAsLong();
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        LongSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asLongSupplierWithThrowable(() -> {
            return 0;
        }).getAsLong();
    }
}
