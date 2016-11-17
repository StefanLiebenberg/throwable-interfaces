package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntFunctionWithThrowable.castIntFunctionWithThrowable;

public class IntFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castIntFunctionWithThrowable((v1) -> {
                throw expected;
            }).apply(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
