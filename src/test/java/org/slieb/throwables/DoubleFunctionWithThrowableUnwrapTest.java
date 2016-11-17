package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleFunctionWithThrowable.castDoubleFunctionWithThrowable;

public class DoubleFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleFunctionWithThrowable((v1) -> {
                throw expected;
            }).apply(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
