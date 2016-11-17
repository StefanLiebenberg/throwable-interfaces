package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.BooleanSupplierWithThrowable.castBooleanSupplierWithThrowable;

public class BooleanSupplierWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castBooleanSupplierWithThrowable(() -> {
                throw expected;
            }).getAsBoolean();
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
