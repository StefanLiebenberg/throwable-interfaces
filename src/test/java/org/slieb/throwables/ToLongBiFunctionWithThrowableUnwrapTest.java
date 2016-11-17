package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToLongBiFunctionWithThrowable.castToLongBiFunctionWithThrowable;

public class ToLongBiFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToLongBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsLong(null, null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
