package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleBinaryOperatorWithThrowable.castDoubleBinaryOperatorWithThrowable;

public class DoubleBinaryOperatorWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleBinaryOperatorWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsDouble(0, 0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
