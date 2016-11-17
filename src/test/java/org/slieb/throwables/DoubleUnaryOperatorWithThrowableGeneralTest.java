package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.DoubleUnaryOperatorWithThrowable.castDoubleUnaryOperatorWithThrowable;

public class DoubleUnaryOperatorWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castDoubleUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).onException(reference::set).applyAsDouble(0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
