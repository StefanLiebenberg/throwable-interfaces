package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.BooleanSupplierWithThrowable.asBooleanSupplierWithThrowable;

public class BooleanSupplierWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asBooleanSupplierWithThrowable(() -> {
            throw new RuntimeException("expected error");
        }).getAsBoolean();
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asBooleanSupplierWithThrowable(() -> {
            throw new Error("expected error");
        }).getAsBoolean();
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        BooleanSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asBooleanSupplierWithThrowable(() -> {
            return false;
        }).getAsBoolean();
    }
}
