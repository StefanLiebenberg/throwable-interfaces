package org.slieb.throwables;

import org.junit.Assert;
import org.junit.Test;

import static org.slieb.throwables.ConsumerWithThrowable.castConsumerWithThrowable;


public class ConsumerWithThrowableTest implements FunctionInterfaceTestInterface {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castConsumerWithThrowable((a) -> {
            throw new Exception("");
        }).accept(null);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castConsumerWithThrowable((a) -> {
            throw new RuntimeException("");
        }).accept(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castConsumerWithThrowable((a) -> {
            throw new Error("");
        }).accept(null);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castConsumerWithThrowable((a) -> {
            throw new Throwable("");
        }).accept(null);
    }


    @Test
    public void testNormalOperation() {
        castConsumerWithThrowable((Integer a) -> Assert.assertEquals(a, a)).accept(1);
    }

    @Override
    public void testAnnotatedWithFunctionalInterface() {
        ConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }
}