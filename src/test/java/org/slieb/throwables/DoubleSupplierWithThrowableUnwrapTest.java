package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleSupplierWithThrowable.castDoubleSupplierWithThrowable;

public class DoubleSupplierWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleSupplierWithThrowable(() -> {
                throw expected;
            }).getAsDouble();
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
