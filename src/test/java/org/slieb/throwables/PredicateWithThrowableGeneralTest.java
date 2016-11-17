package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.PredicateWithThrowable.castPredicateWithThrowable;

public class PredicateWithThrowableGeneralTest {

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castPredicateWithThrowable((v1) -> {
                throw expected;
            }).onException(reference::set).test(null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
