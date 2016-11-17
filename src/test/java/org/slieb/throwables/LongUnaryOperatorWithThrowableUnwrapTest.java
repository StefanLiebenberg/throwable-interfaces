package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongUnaryOperatorWithThrowable.castLongUnaryOperatorWithThrowable;

public class LongUnaryOperatorWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).applyAsLong(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
