package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleToLongFunctionWithThrowable.castDoubleToLongFunctionWithThrowable;

public class DoubleToLongFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleToLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsLong(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
