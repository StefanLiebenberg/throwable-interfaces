package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntSupplierWithThrowable.castIntSupplierWithThrowable;

public class IntSupplierWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castIntSupplierWithThrowable(() -> {
                throw expected;
            }).getAsInt();
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
