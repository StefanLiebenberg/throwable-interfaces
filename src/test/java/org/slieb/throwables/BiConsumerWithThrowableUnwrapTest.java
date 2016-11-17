package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.BiConsumerWithThrowable.castBiConsumerWithThrowable;

public class BiConsumerWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castBiConsumerWithThrowable((v1, v2) -> {
                throw expected;
            }).accept(null, null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
