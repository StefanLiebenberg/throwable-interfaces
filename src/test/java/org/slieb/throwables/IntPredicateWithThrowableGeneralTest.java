package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.IntPredicateWithThrowable.castIntPredicateWithThrowable;

public class IntPredicateWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castIntPredicateWithThrowable((v1) -> {
                throw expected;
            }).onException(reference::set).test(0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
