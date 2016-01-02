package org.slieb.throwables;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.slieb.throwables.BiFunctionWithThrowable.castBiFunctionWithThrowable;

/**
 * This tests the BiFunctionWithThrowable class to ensure that it behaves as expected.
 */
public class BiFunctionWithThrowableTest {


    @Test(expected = SuppressedException.class)
    public void testThrowCheckedException() {
        castBiFunctionWithThrowable((a, b) -> {
            throw new Exception("");
        }).apply(null, null);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowRuntimeException() {
        castBiFunctionWithThrowable((a, b) -> {
            throw new RuntimeException("");
        }).apply(null, null);
    }

    @Test(expected = Error.class)
    public void testThrowError() {
        castBiFunctionWithThrowable((a, b) -> {
            throw new Error("");
        }).apply(null, null);
    }

    @Test(expected = Throwable.class)
    public void testThrowThrowable() {
        castBiFunctionWithThrowable((a, b) -> {
            throw new Throwable("");
        }).apply(null, null);
    }

    @Test
    public void testNormalOperation() {
        assertEquals(Integer.valueOf(3),
                castBiFunctionWithThrowable((Integer a, Integer b) -> a + b).apply(1, 2));
    }


}
