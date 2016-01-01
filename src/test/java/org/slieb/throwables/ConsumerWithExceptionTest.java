package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ConsumerWithException.castConsumerWithException;


public class ConsumerWithExceptionTest {

    // sum biFunction method that produces runtime exception
    private void throwsStateException(Object value) {
        throw new IllegalStateException("invalid state");
    }

    public void throwCheckedException(Object object) throws Exception {
        throw new Exception("null");
    }


    @Test
    public void testCanCastMethodReferencesThatThrowException() throws Exception {
        castConsumerWithException(this::throwCheckedException);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionIsChangedToRuntimeException() {
        castConsumerWithException(this::throwCheckedException).accept(new Object());
    }

    @Test(expected = IllegalStateException.class)
    public void testRuntimeExceptionsAreNotCaught() {
        castConsumerWithException(this::throwsStateException).accept(new Object());
    }

}