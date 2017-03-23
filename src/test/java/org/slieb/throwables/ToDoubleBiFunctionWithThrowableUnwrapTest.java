package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ToDoubleBiFunctionWithThrowable.castToDoubleBiFunctionWithThrowable;
import static org.junit.Assert.assertEquals;
public class ToDoubleBiFunctionWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            return castToDoubleBiFunctionWithThrowable((v1, v2) -> {
                throw expected;
            }).applyAsDouble(null, null);
        }, CustomException.class);
    }
}
