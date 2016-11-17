package org.slieb.throwables;

import org.junit.Test;

import static org.slieb.throwables.ToDoubleFunctionWithThrowable.castToDoubleFunctionWithThrowable;

public class ToDoubleFunctionWithThrowableUnwrapTest {

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToDoubleFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsDouble(null);
        }, CustomException.class);
    }

    private class CustomException extends Exception {}
}
