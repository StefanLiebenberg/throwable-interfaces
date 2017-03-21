package org.slieb.throwables;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.PredicateWithThrowable.castPredicateWithThrowable;

public class PredicateWithThrowableGeneralTest {

    @Test
    public void testReturnTypeExceptionWithTrue() {
        boolean expected = true;
        boolean result = castPredicateWithThrowable((v1) -> {
            throw new Exception("expect exception");
        }).thatReturnsOnCatch(expected).test(null);
        assertEquals(expected, result);
    }

    @Test
    public void testReturnTypeExceptionWithFalse() {
        boolean expected = false;
        boolean result = castPredicateWithThrowable((v1) -> {
            throw new Exception("expect exception");
        }).thatReturnsOnCatch(expected).test(null);
        assertEquals(expected, result);
    }

    @Test
    public void testNormalOperation() {
        boolean expected = true;
        boolean result = castPredicateWithThrowable((v1) -> expected).thatReturnsOnCatch(false).test(null);
        assertEquals(expected, result);
    }

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
