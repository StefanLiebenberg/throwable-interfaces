package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.IntBinaryOperatorWithThrowable.castIntBinaryOperatorWithThrowable;

public class IntBinaryOperatorWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castIntBinaryOperatorWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsInt(0, 0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
