package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.BinaryOperatorWithThrowable.castBinaryOperatorWithThrowable;

public class BinaryOperatorWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castBinaryOperatorWithThrowable((v1, v2) -> {
                throw expected;
            }).apply(null, null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
