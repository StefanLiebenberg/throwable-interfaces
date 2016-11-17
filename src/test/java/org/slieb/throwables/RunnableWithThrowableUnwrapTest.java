package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.RunnableWithThrowable.castRunnableWithThrowable;

public class RunnableWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castRunnableWithThrowable(() -> {
                throw expected;
            }).run();
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
