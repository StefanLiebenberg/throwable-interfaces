package org.slieb.throwables;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.SupplierWithThrowable.castSupplierWithThrowable;


public class SupplierWithThrowableTest {

    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castSupplierWithThrowable(() -> {
            throw new Exception("");
        }).get();
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castSupplierWithThrowable(() -> {
            throw new RuntimeException("");
        }).get();
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castSupplierWithThrowable(() -> {
            throw new Error("");
        }).get();
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castSupplierWithThrowable(() -> {
            throw new Throwable("");
        }).get();
    }


    @Test
    public void testNormalOperation() {
        assertEquals(Integer.valueOf(100), castSupplierWithThrowable(() -> 100).get());
    }

}