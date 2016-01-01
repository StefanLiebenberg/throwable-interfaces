package org.slieb.throwables;

import org.junit.Test;


public class SupplierWithExceptionTest {
    // sum biFunction method that produces runtime exception
    private String throwsStateException() {
        throw new IllegalStateException("invalid state");
    }

    public Object throwCheckedException() throws Exception {
        throw new Exception("null");
    }


    @Test
    public void testCanCastMethodReferencesThatThrowException() throws Exception {
        SupplierWithException.castSupplierWithException(this::throwCheckedException);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionIsChangedToRuntimeException() {
        SupplierWithException.castSupplierWithException(this::throwCheckedException).get();
    }

    @Test(expected = IllegalStateException.class)
    public void testRuntimeExceptionsAreNotCaught() {
        SupplierWithException.castSupplierWithException(this::throwsStateException).get();
    }


}