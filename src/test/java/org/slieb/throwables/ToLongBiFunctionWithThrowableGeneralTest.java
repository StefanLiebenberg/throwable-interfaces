package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.ToLongBiFunctionWithThrowable.castToLongBiFunctionWithThrowable;

public class ToLongBiFunctionWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castToLongBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).onException(reference::set).applyAsLong(null, null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
