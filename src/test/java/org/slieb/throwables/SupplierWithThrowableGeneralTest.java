package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.SupplierWithThrowable.castSupplierWithThrowable;

public class SupplierWithThrowableGeneralTest {

    @Test
    public void testReturnTypeException() {
        Object expected = new Object();
        Object result = castSupplierWithThrowable(() -> {
            throw new Exception("expect exception");
        }).thatReturnsOnCatch(expected).get();
        assertEquals(expected, result);
    }

    @Test
    public void testNormalOperation() {
        Object expected = new Object();
        Object result = castSupplierWithThrowable(() -> expected).thatReturnsOnCatch(null).get();
        assertEquals(expected, result);
    }

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castSupplierWithThrowable(() -> {
                throw expected;
            }).onException(reference::set).get();
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
