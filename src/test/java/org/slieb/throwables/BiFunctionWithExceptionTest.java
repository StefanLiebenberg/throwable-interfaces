package org.slieb.throwables;

import org.junit.Test;
import org.slieb.throwables.BiFunctionWithException;

/**
 * This tests the BiFunctionWithException class to ensure that it behaves as expected.
 */
public class BiFunctionWithExceptionTest {

    // sum biFunction method
    private Integer sumPositives(Integer a, Integer b) throws Exception {
        if (a < 0 || b < 0) throw new Exception("cant sum values");
        return a + b;
    }

    // sum biFunction method that produces runtime exception
    private Integer throwsStateException(Integer i1, Integer i2) {
        throw new IllegalStateException("invalid state");
    }

    @Test
    public void testCanCastMethodReferencesThatThrowException() throws Exception {
        BiFunctionWithException.castBiFunctionWithException(this::sumPositives);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionIsChangedToRuntimeException() {
        BiFunctionWithException.castBiFunctionWithException(this::sumPositives).apply(-1, -1);
    }

    @Test(expected = IllegalStateException.class)
    public void testRuntimeExceptionsAreNotCaught() {
        BiFunctionWithException.castBiFunctionWithException(this::throwsStateException).apply(1, 2);
    }
}
