package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.BooleanSupplierWithThrowable.castBooleanSupplierWithThrowable;

public class BooleanSupplierWithThrowableGeneralTest {

    @Test
    public void testReturnTypeExceptionWithTrue() {
        boolean expected = true;
        boolean result = castBooleanSupplierWithThrowable(() -> {
            throw new Exception("expect exception");
        }).thatReturnsOnCatch(expected).getAsBoolean();
        assertEquals(expected, result);
    }

    @Test
    public void testReturnTypeExceptionWithFalse() {
        boolean expected = false;
        boolean result = castBooleanSupplierWithThrowable(() -> {
            throw new Exception("expect exception");
        }).thatReturnsOnCatch(expected).getAsBoolean();
        assertEquals(expected, result);
    }

    @Test
    public void testNormalOperation() {
        boolean expected = true;
        boolean result = castBooleanSupplierWithThrowable(() -> expected).thatReturnsOnCatch(false).getAsBoolean();
        assertEquals(expected, result);
    }

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castBooleanSupplierWithThrowable(() -> {
                throw expected;
            }).onException(reference::set).getAsBoolean();
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
