package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.FunctionWithThrowable.castFunctionWithThrowable;

public class FunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castFunctionWithThrowable((v1) -> {
                throw expected;
            }).apply(null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
