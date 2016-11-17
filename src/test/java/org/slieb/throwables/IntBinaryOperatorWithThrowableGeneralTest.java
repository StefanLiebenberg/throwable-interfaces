package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.IntBinaryOperatorWithThrowable.castIntBinaryOperatorWithThrowable;

public class IntBinaryOperatorWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castIntBinaryOperatorWithThrowable((v1, v2) -> {
                throw expected;
            }).onException(reference::set).applyAsInt(0, 0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
