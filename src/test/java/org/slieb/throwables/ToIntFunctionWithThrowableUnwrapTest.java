package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToIntFunctionWithThrowable.castToIntFunctionWithThrowable;

public class ToIntFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToIntFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsInt(null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
