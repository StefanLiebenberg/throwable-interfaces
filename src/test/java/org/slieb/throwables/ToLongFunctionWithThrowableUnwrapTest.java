package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToLongFunctionWithThrowable.castToLongFunctionWithThrowable;

public class ToLongFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsLong(null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
