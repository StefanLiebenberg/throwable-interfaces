package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.DoubleConsumerWithThrowable.castDoubleConsumerWithThrowable;

public class DoubleConsumerWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castDoubleConsumerWithThrowable((v1) -> {
                throw expected;
            }).accept(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
