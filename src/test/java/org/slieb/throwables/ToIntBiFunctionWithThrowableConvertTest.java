package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToIntBiFunctionWithThrowable.asToIntBiFunctionWithThrowable;

public class ToIntBiFunctionWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asToIntBiFunctionWithThrowable((v1, v2) -> {
            throw new RuntimeException("expected error");
        }).applyAsInt(null, null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asToIntBiFunctionWithThrowable((v1, v2) -> {
            throw new Error("expected error");
        }).applyAsInt(null, null);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        ToIntBiFunctionWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asToIntBiFunctionWithThrowable((v1, v2) -> {
            return 0;
        }).applyAsInt(null, null);
    }
}
