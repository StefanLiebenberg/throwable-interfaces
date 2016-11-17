package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ObjLongConsumerWithThrowable.asObjLongConsumerWithThrowable;

public class ObjLongConsumerWithThrowableConvertTest {

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        asObjLongConsumerWithThrowable((v1, v2) -> {
            throw new RuntimeException("expected error");
        }).accept(null, 0);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        asObjLongConsumerWithThrowable((v1, v2) -> {
            throw new Error("expected error");
        }).accept(null, 0);
    }

    @Test
    public void testAnnotatedWithFunctionalInterface() {
        ObjLongConsumerWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }

    @Test
    public void testNormalOperation() {
        asObjLongConsumerWithThrowable((v1, v2) -> {
        }).accept(null, 0);
    }
}
