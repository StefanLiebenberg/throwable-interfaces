package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.SupplierWithThrowable.asSupplierWithThrowable;

public class SupplierWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asSupplierWithThrowable(() -> {
            throw new RuntimeException("expected error");
        }).get();
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asSupplierWithThrowable(() -> {
            throw new Error("expected error");
        }).get();
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        SupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asSupplierWithThrowable(() -> {
            return null;
        }).get();
    }
}
