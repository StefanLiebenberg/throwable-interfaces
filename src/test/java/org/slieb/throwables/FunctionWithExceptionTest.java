package org.slieb.throwables;

import org.junit.Test;
import org.slieb.throwables.FunctionWithException;


public class FunctionWithExceptionTest {

    private boolean throwsStateException(Object one) {
        throw new IllegalStateException("invalid state");
    }

    public boolean throwCheckedException(Object one) throws Exception {
        throw new Exception("null");
    }


    @Test
    public void testCanCastMethodReferencesThatThrowException() throws Exception {
        FunctionWithException.castFunctionWithException(this::throwCheckedException);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionIsChangedToRuntimeException() {
        FunctionWithException.castFunctionWithException(this::throwCheckedException).apply(new Object());
    }

    @Test(expected = IllegalStateException.class)
    public void testRuntimeExceptionsAreNotCaught() {
        FunctionWithException.castFunctionWithException(this::throwsStateException).apply(new Object());
    }

}