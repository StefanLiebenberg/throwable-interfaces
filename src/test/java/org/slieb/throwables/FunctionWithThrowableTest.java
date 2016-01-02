package org.slieb.throwables;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.FunctionWithThrowable.castFunctionWithThrowable;


public class FunctionWithThrowableTest {
    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castFunctionWithThrowable((a) -> {
            throw new Exception("");
        }).apply(null);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castFunctionWithThrowable((a) -> {
            throw new RuntimeException("");
        }).apply(null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castFunctionWithThrowable((a) -> {
            throw new Error("");
        }).apply(null);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castFunctionWithThrowable((a) -> {
            throw new Throwable("");
        }).apply(null);
    }

    @Test
    public void testNormalOperation() {
        assertEquals(Integer.valueOf(2),
                castFunctionWithThrowable((Integer a) -> a + 1).apply(1));
    }


}