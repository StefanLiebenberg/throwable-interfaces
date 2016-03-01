package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.ClosureWithThrowable.castClosureWithThrowable;
import static org.junit.Assert.assertEquals;
public class ClosureWithThrowableUnwrapTest {

    private class CustomException extends Exception {}

    @Test(expected = CustomException.class)
    public void testUnwrap() throws CustomException {
        CustomException expected = new CustomException();
        SuppressedException.unwrapSuppressedException(() -> {
            castClosureWithThrowable(() -> {
                throw expected;
            }).call();
        }, CustomException.class);
    }
}
