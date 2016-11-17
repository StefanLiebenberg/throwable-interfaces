package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ConsumerWithThrowable.castConsumerWithThrowable;

public class ConsumerWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castConsumerWithThrowable((v1) -> {
                throw expected;
            }).accept(null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
