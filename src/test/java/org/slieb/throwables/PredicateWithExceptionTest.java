package org.slieb.throwables;

import org.junit.Test;
import org.slieb.throwables.PredicateWithException;


public class PredicateWithExceptionTest {
    // sum biFunction method that produces runtime exception
    private boolean throwsStateException(Object object) {
        throw new IllegalStateException("invalid state");
    }

    public boolean throwCheckedException(Object object) throws Exception {
        throw new Exception("null");
    }


    @Test
    public void testCanCastMethodReferencesThatThrowException() throws Exception {
        PredicateWithException.castPredicateWithException(this::throwCheckedException);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionIsChangedToRuntimeException() {
        PredicateWithException.castPredicateWithException(this::throwCheckedException).test(new Object());
    }

    @Test(expected = IllegalStateException.class)
    public void testRuntimeExceptionsAreNotCaught() {
        PredicateWithException.castPredicateWithException(this::throwsStateException).test(new Object());
    }


}