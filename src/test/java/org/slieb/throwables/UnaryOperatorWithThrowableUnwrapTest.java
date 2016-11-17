package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.UnaryOperatorWithThrowable.castUnaryOperatorWithThrowable;

public class UnaryOperatorWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castUnaryOperatorWithThrowable((v1) -> {
                throw expected;
            }).apply(null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
