package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.DoubleToLongFunctionWithThrowable.castDoubleToLongFunctionWithThrowable;

public class DoubleToLongFunctionWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castDoubleToLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).onException(reference::set).applyAsLong(0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
