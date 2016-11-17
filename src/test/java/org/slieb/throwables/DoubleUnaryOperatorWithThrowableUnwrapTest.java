package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleUnaryOperatorWithThrowable.castDoubleUnaryOperatorWithThrowable;

public class DoubleUnaryOperatorWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castDoubleUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).applyAsDouble(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
