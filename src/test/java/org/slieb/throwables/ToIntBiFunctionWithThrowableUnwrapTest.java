package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToIntBiFunctionWithThrowable.castToIntBiFunctionWithThrowable;

public class ToIntBiFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToIntBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsInt(null, null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
