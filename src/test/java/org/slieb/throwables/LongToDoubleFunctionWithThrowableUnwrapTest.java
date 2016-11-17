package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.LongToDoubleFunctionWithThrowable.castLongToDoubleFunctionWithThrowable;

public class LongToDoubleFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castLongToDoubleFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsDouble(0);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
