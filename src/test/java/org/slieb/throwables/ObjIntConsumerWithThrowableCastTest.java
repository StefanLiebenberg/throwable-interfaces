package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ObjIntConsumerWithThrowable.castObjIntConsumerWithThrowable;

public class ObjIntConsumerWithThrowableCastTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castObjIntConsumerWithThrowable((v1, v2) -> {
            throw new Exception("expected error");
        }).accept(null, 0);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castObjIntConsumerWithThrowable((v1, v2) -> {
            throw new RuntimeException("expected error");
        }).accept(null, 0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castObjIntConsumerWithThrowable((v1, v2) -> {
            throw new Error("expected error");
        }).accept(null, 0);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castObjIntConsumerWithThrowable((v1, v2) -> {
            throw new Throwable("expected throwable");
        }).accept(null, 0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        ObjIntConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        castObjIntConsumerWithThrowable((v1, v2) -> {
        }).accept(null, 0);
    }
}
