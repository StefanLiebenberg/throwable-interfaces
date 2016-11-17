package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.ToDoubleBiFunctionWithThrowable.castToDoubleBiFunctionWithThrowable;

public class ToDoubleBiFunctionWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castToDoubleBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).onException(reference::set).applyAsDouble(null, null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
