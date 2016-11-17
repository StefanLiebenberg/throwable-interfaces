package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleToIntFunctionWithThrowable.castDoubleToIntFunctionWithThrowable;

public class DoubleToIntFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleToIntFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsInt(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
