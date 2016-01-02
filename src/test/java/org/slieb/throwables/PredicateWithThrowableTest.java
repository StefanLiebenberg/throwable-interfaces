package org.slieb.throwables;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.slieb.throwables.PredicateWithThrowable.castPredicateWithThrowable;


public class PredicateWithThrowableTest implements FunctionInterfaceTestInterface {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castPredicateWithThrowable((a) -> {
            throw new Exception("");
        }).test(null);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castPredicateWithThrowable((a) -> {
            throw new RuntimeException("");
        }).test(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castPredicateWithThrowable((a) -> {
            throw new Error("");
        }).test(null);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castPredicateWithThrowable((a) -> {
            throw new Throwable("");
        }).test(null);
    }

    @Test
    public void testNormalOperation() {
        assertTrue(castPredicateWithThrowable((Integer a) -> a > 5).test(10));
        assertFalse(castPredicateWithThrowable((Integer a) -> a > 5).test(0));
    }

    @Override
    public void testAnnotatedWithFunctionalInterface() {
        PredicateWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }


}