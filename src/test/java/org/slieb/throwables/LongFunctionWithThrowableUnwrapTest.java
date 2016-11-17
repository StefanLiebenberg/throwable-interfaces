package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongFunctionWithThrowable.castLongFunctionWithThrowable;

public class LongFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongFunctionWithThrowable((v1) -> {
                throw expected;
            }).apply(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
