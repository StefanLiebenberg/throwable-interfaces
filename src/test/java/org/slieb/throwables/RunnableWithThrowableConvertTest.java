package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.RunnableWithThrowable.asRunnableWithThrowable;

public class RunnableWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asRunnableWithThrowable(() -> {
            throw new RuntimeException("expected error");
        }).run();
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asRunnableWithThrowable(() -> {
            throw new Error("expected error");
        }).run();
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        RunnableWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asRunnableWithThrowable(() -> {
        }).run();
    }
}
