package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongBinaryOperatorWithThrowable.castLongBinaryOperatorWithThrowable;

public class LongBinaryOperatorWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongBinaryOperatorWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsLong(0, 0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
