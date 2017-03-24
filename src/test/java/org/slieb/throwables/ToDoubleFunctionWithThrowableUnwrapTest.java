package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToDoubleFunctionWithThrowable.castToDoubleFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToDoubleFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToDoubleFunctionWithThrowable((v1) -> {
                throw expected;
            }).applyAsDouble(null);
        }, CustomException.class);
    }
}
