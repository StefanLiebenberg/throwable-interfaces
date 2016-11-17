package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.UnaryOperatorWithThrowable.castUnaryOperatorWithThrowable;

public class UnaryOperatorWithThrowableGeneralTest {

    @Test
    public void testReturnTypeException() {
        Object expected = new Object();
        Object result = castUnaryOperatorWithThrowable((v1) -> {
            throw new Exception("expect exception");
        }).thatReturnsOnCatch(expected).apply(null);
        assertEquals(expected, result);
    }

    @Test
    public void testNormalOperation() {
        Object expected = new Object();
        Object result = castUnaryOperatorWithThrowable((v1) -> expected).thatReturnsOnCatch(null).apply(null);
        assertEquals(expected, result);
    }

    @Test
    public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
            castUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).onException(reference::set).apply(null);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
    }
}
