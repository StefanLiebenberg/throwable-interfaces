package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.DoubleSupplierWithThrowable.castDoubleSupplierWithThrowable;

public class DoubleSupplierWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castDoubleSupplierWithThrowable(() -> {
                throw expected;
            }).onException(reference::set).getAsDouble();
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
