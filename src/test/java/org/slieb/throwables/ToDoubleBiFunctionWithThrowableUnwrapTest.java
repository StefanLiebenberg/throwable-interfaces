package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToDoubleBiFunctionWithThrowable.castToDoubleBiFunctionWithThrowable;

public class ToDoubleBiFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToDoubleBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsDouble(null, null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
