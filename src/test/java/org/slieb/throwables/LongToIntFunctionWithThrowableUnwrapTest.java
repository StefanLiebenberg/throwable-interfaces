package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongToIntFunctionWithThrowable.castLongToIntFunctionWithThrowable;

public class LongToIntFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongToIntFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsInt(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
