package org.slieb.throwables;

import org.junit.Assert;
import org.junit.Test;

import static org.slieb.throwables.IntSupplierWithThrowable.castIntSupplierWithThrowable;

public class IntSupplierWithThrowableTest implements FunctionInterfaceTestInterface {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castIntSupplierWithThrowable(() -> {
            throw new Exception("");
        }).getAsInt();
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castIntSupplierWithThrowable(() -> {
            throw new RuntimeException("");
        }).getAsInt();
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castIntSupplierWithThrowable(() -> {
            throw new Error("");
        }).getAsInt();
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castIntSupplierWithThrowable(() -> {
            throw new Throwable("");
        }).getAsInt();
    }


    @Test
    public void testNormalOperation() {
        Assert.assertEquals(100, castIntSupplierWithThrowable(() -> 100).getAsInt());
    }

    @Override
    public void testAnnotatedWithFunctionalInterface() {
        IntSupplierWithThrowable.class.isAnnotationPresent(FunctionalInterface.class);
    }
}