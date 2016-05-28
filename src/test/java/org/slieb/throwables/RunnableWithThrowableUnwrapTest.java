package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.RunnableWithThrowable.castRunnableWithThrowable;
import static org.junit.Assert.assertEquals;
public class RunnableWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castRunnableWithThrowable(() -> {
                throw expected;
            }).run();
        }, CustomException.class);
    }
}
